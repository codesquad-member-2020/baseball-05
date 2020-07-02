//
//  PlayViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayViewController: UIViewController {
    @IBOutlet weak var headerView: GameHeaderView!
    @IBOutlet weak var currentPlayerTable: UITableView!
    @IBOutlet weak var roundInfoTable: UITableView!
    @IBOutlet weak var scoreView: ScoreView!
    @IBOutlet weak var boardView: BoardView!
    
    private var playTablesViewModel: PlayTablesViewModel!
    private var scoreViewModel: ScoreViewModel!
    private var boardViewModel: BoardViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
        configureObservers()
        configurePitchButtonTarget()
        configureUseCase()
    }
    
    private func configureTitleView() {
        headerView.delegate = self
    }
    
    private func configureObservers()  {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(configureScoreView),
                                               name: ScoreViewModel.Notification.scoreDataDidChange,
                                               object: scoreViewModel)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(configureBoardView),
                                               name: BoardViewModel.Notification.boardModelsDidChange,
                                               object: boardViewModel)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(updateTablesCell),
                                               name: PlayTablesViewModel.Notification.tablesModelDidChange,
                                               object: playTablesViewModel)
    }
    
    @objc private func configureScoreView() {
        DispatchQueue.main.async {
            self.scoreView.awayTeamName.text = self.scoreViewModel.awayTeamName
            self.scoreView.awayTeamScore.text = String(self.scoreViewModel.awayScore)
            self.scoreView.homeTeamName.text = self.scoreViewModel.homeTeamName
            self.scoreView.homeTeamScore.text = String(self.scoreViewModel.homeScore)
        }
    }
    
    @objc private func configureBoardView() {
        DispatchQueue.main.async {
            self.boardView.configureSBOsView(sbo: self.boardViewModel.sboViewModel.sbo)
            
            let inningViewModel = self.boardViewModel.inningViewModel
            self.boardView.inningLabel.text = "\(String(inningViewModel.userInning.inningCount))회"
            self.boardView.halfLabel.text = inningViewModel.userInning.half.description
            self.boardView.offenseOrDefense.text = inningViewModel.offenseOrDefenseText()
            
            guard let isOffense = inningViewModel.isOffense else { return }
            self.boardView.pitchButton.isHidden = isOffense ? true : false
        }
    }
    
    private func configurePitchButtonTarget() {
        boardView.pitchButton.addTarget(self,
                                        action: #selector(requestPitch),
                                        for: .touchUpInside)
    }
    
    @objc private func requestPitch() {
        guard let roomID = roomID else { return }
        PitchUseCase.requestPitch(from: PitchUseCase.PitchRequest(matchID: roomID),
                                  with: PitchUseCase.PitchTask(networkDispatcher: NetworkManager()))
        { resultResponse in
            guard let resultResponse = resultResponse else { return }
            if resultResponse.result == .success {
                self.configureUseCase()
            }
        }
    }
    
    @objc private func updateTablesCell() {
        DispatchQueue.main.async {
            self.currentPlayerTable.reloadData()
            self.roundInfoTable.reloadData()
        }
    }
    
    var roomID: Int?
    var userTeamKind: TeamKind?
    private func configureUseCase() {
        guard let roomID = roomID else { return }
        PlayUseCase.reqeustPlayData(from: PlayUseCase.PlayDataRequest(matchID: roomID), with: PlayUseCase.PlayDataTask(networkDispatcher: MockRoundsSuccess())) { playDataResponse in
            guard let playDataResponse = playDataResponse else { return }
            self.configureScoreViewModel(playDataResponse)
            self.configureBoardViewModel(playDataResponse)
            self.configurePlayTableViewModel(playDataResponse)
            
            guard let isOffense = self.boardViewModel.inningViewModel.isOffense, isOffense else { return }
            if isOffense {
                self.requestOppenentPitchCompleted()
            }
        }
    }
    
    private let updatePlayDataQueue = DispatchQueue(label: "updatePlayData")
    private func requestOppenentPitchCompleted() {
        guard let roomID = roomID else { return }
        PlayUseCase.reqeustPlayData(from: PlayUseCase.PlayDataRequest(matchID: roomID), with: PlayUseCase.PlayDataTask(networkDispatcher: NetworkManager())) { playDataResponse in
            guard let playDataResponse = playDataResponse else { return }
            guard let isOffense = self.isOffense(half: playDataResponse.inning.half) else { return }
            if isOffense {
                self.updatePlayDataQueue.asyncAfter(deadline: .now() + 3) {
                    self.requestOppenentPitchCompleted()
                }
            } else {
                self.configureScoreViewModel(playDataResponse)
                self.configureBoardViewModel(playDataResponse)
                self.configurePlayTableViewModel(playDataResponse)
            }
        }
    }
    
    private func isOffense(half: Half) -> Bool? {
        guard let kind = userTeamKind else { return nil }
        switch kind {
        case .away:
            switch half {
            case .top:
                return true
            case .bottom:
                return false
            }
        case .home:
            switch half {
            case .top:
                return false
            case .bottom:
                return true
            }
        }
    }
    
    private func configureScoreViewModel(_ playDataResponse: PlayDataResponse) {
        self.scoreViewModel = ScoreViewModel(awayTeamName: playDataResponse.awayTeam.name,
                                             awayScore: playDataResponse.awayTeam.score,
                                             homeTeamName: playDataResponse.homeTeam.name,
                                             homeScore: playDataResponse.homeTeam.score)
    }
    
    private func configureBoardViewModel(_ playDataResponse: PlayDataResponse) {
        guard let latestPlate = playDataResponse.plates.last else { return }
        
        let outCount = latestPlate.outs
        if let latestRound = latestPlate.rounds.last  {
            self.boardViewModel = BoardViewModel(sboViewModel: SBOsViewModel(sbo: SBO(strikeCount: latestRound.strike,
                                                                                      ballCount: latestRound.ball,
                                                                                      outCount: outCount)),
                                                 inningViewModel: UserInningViewModel(userInning: UserInning(inning: playDataResponse.inning, kind: userTeamKind))
            )
        } else {
            self.boardViewModel = BoardViewModel(sboViewModel: SBOsViewModel(sbo: SBO(strikeCount: 0,
                                                                                      ballCount: 0,
                                                                                      outCount: outCount)),
                                                 inningViewModel: UserInningViewModel(userInning: UserInning(inning: playDataResponse.inning, kind: userTeamKind))
            )
        }
    }
    
    private func configurePlayTableViewModel(_ playDataResponse: PlayDataResponse) {
        let pitcher = CurrentPitcher(name: playDataResponse.currentPlayers.pitcher.name,
                                     pitches: playDataResponse.currentPlayers.pitcher.pitches)
        let batter = CurrentBatter(name: playDataResponse.currentPlayers.batter.name,
                                   mounts: playDataResponse.currentPlayers.batter.mounts,
                                   hits: playDataResponse.currentPlayers.batter.hits)
        guard let isOffense = isOffense(half: playDataResponse.inning.half) else { return }
        
        if let latestPlate = playDataResponse.plates.last {
            self.playTablesViewModel = PlayTablesViewModel(currentPlayers: [pitcher, batter],
                                                           rounds: latestPlate.rounds,
                                                           currentPlayerTableView: currentPlayerTable,
                                                           roundInfoTableView: roundInfoTable,
                                                           isOffense: isOffense)
        } else {
            self.playTablesViewModel = PlayTablesViewModel(currentPlayers: [pitcher, batter],
                                                           rounds: [],
                                                           currentPlayerTableView: currentPlayerTable,
                                                           roundInfoTableView: roundInfoTable,
                                                           isOffense:  isOffense)
        }
        configureCurrentPlayerTableDataSource()
        configureRoundInfoTableDataSource()
    }
    
    private func configureCurrentPlayerTableDataSource() {
        DispatchQueue.main.async {
            self.currentPlayerTable.dataSource = self.playTablesViewModel
        }
    }
    
    private func configureRoundInfoTableDataSource() {
        DispatchQueue.main.async {
            self.roundInfoTable.dataSource = self.playTablesViewModel
        }
    }
}

extension PlayViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true) {
            self.updatePlayDataQueue.suspend()
        }
    }
}
