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
        configurePlayTableViewModel()
        configureObservers()
        configureUseCase()
    }
    
    private func configureTitleView() {
        headerView.delegate = self
    }
    
    private func configurePlayTableViewModel() {
        playTablesViewModel = PlayTablesViewModel(currentPlayers: [CurrentPlayer(name: "최동원",
                                                                                 mounts: 39,
                                                                                 hits: 0,
                                                                                 isPitcher: true),
                                                                   CurrentPlayer(name: "김광진",
                                                                                 mounts: 1,
                                                                                 hits: 0,
                                                                                 isPitcher: false)],
                                                  roundInfos: [RoundInfo(decision: "볼", strike: 1, ball: 1),
                                                               RoundInfo(decision: "볼", strike: 1, ball: 2),
                                                               RoundInfo(decision: "볼", strike: 1, ball: 3),
                                                               RoundInfo(decision: "스트라이크", strike: 2, ball: 3)],
                                                  currentPlayerTableView: currentPlayerTable,
                                                  roundInfoTableView: roundInfoTable)
        configureCurrentPlayerTableDataSource()
        configureRoundInfoTableDataSource()
    }
    
    private func configureCurrentPlayerTableDataSource() {
        currentPlayerTable.dataSource = playTablesViewModel
    }
    
    private func configureRoundInfoTableDataSource() {
        roundInfoTable.dataSource = playTablesViewModel
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
            self.boardView.configureInningInfoLabels(inning: self.boardViewModel.inningViewModel.inning)
        }
    }
    
    var roomID: Int?
    private func configureUseCase() {
        guard let roomID = roomID else { return }
        PlayUseCase.reqeustPlayData(from: PlayUseCase.PlayDataRequest(matchID: roomID), with: PlayUseCase.PlayDataTask(networkDispatcher: NetworkManager())) { playDataResponse in
            guard let playDataResponse = playDataResponse else { return }
            self.configureScoreViewModel(playDataResponse)
            self.configureBoardViewModel(playDataResponse)
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
                                                 inningViewModel: InningViewModel(inning: playDataResponse.inning)
            )
        } else {
            self.boardViewModel = BoardViewModel(sboViewModel: SBOsViewModel(sbo: SBO(strikeCount: 0,
                                                                                      ballCount: 0,
                                                                                      outCount: outCount)),
                                                 inningViewModel: InningViewModel(inning: playDataResponse.inning))
        }
    }
}

extension PlayViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true)
    }
}
