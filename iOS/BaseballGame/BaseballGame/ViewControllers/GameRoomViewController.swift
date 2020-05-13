//
//  ViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomViewController: UIViewController, IdentifiableViewController {
    //MARK:- Internal properties
    private let gameRoomTitleLabel: TitleLabel = {
        let label = TitleLabel()
        label.text = GameRoomViewModels.titleText
        label.textColor = GameRoomViewModels.titleColor
        return label
    }()
    private let prevButton = PrevButton()
    private var gameRoomCollectionView: GameRoomCollectionView!
    private var gameRoomViewModels: GameRoomViewModels!
    
    deinit {
        prevButton.removeTarget(self, action: #selector(prevButtonDidTouch), for: .touchUpInside)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureGameTitleLabel()
        configurePrevButton()
        configureCollectionView()
        configureObserver()
        configureUseCase()
    }
    
    private func configureGameTitleLabel() {
        view.addSubview(gameRoomTitleLabel)
        
        gameRoomTitleLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        gameRoomTitleLabel.topAnchor.constraint(equalTo: safeArea.topAnchor,
                                                constant: 23).isActive = true
    }
    
    private func configurePrevButton() {
        configurePrevButtonConstraints()
        configureDelegate()
    }
    
    private func configureDelegate() {
        prevButton.addTarget(self, action: #selector(prevButtonDidTouch), for: .touchUpInside)
    }
    
    @objc private func prevButtonDidTouch() {
        navigationController?.popViewController(animated: true)
    }
    
    private func configurePrevButtonConstraints() {
        view.addSubview(prevButton)
        
        let safeArea = view.safeAreaLayoutGuide
        prevButton.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor,
                                            constant: 15).isActive = true
        prevButton.centerYAnchor.constraint(equalTo: gameRoomTitleLabel.centerYAnchor).isActive = true
    }
    
    private func configureCollectionView() {
        gameRoomCollectionView = GameRoomCollectionView(collectionViewLayout:
            GameRoomCollectionViewFlowLayout(superFrame: view.frame))
        gameRoomCollectionView.register(GameRoomCell.self, forCellWithReuseIdentifier: GameRoomCell.identifier)
        gameRoomCollectionView.delegate = self
        configureCollectionViewConstraints()
    }
    
    private func configureCollectionViewConstraints() {
        view.addSubview(gameRoomCollectionView)
        
        gameRoomCollectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        gameRoomCollectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        gameRoomCollectionView.topAnchor.constraint(equalTo: gameRoomTitleLabel.bottomAnchor, constant: 43).isActive = true
        gameRoomCollectionView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
    }
    
    private func configureObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(updateGameCollectionView),
                                               name: GameRoomViewModels.Notification.gameViewModelsDidChange,
                                               object: gameRoomViewModels)
    }
    
    @objc private func updateGameCollectionView() {
        DispatchQueue.main.async {
            self.gameRoomCollectionView.reloadData()
        }
    }
    
    private func configureUseCase() {
        GameRoomUseCase.requestGameRoom(from: GameRoomUseCase.GameRoomRequest(),
                                        with: GameRoomUseCase.GameRoomTask(networkDispatcher: MockGameRoomSuccess()))
        { gameRooms in
            guard let gameRooms = gameRooms else { return }
            self.configureGameRoomViewModels(gameRooms: gameRooms)
        }
    }
    
    private func configureGameRoomViewModels(gameRooms: [GameRoom]) {
        self.gameRoomViewModels = GameRoomViewModels(with: gameRooms)
        configureGameRoomDataSource()
    }
    
    private func configureGameRoomDataSource() {
        DispatchQueue.main.async {
            self.gameRoomCollectionView.dataSource = self.gameRoomViewModels
        }
    }
}

extension GameRoomViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let gameRoom = gameRoomViewModels.viewModel(at: indexPath.row)?.gameRoom else { return }
        
        if gameRoom.selectable {
            showActionSheet(gameRoom: gameRoom)
        } else {
            showAlert()
        }
    }
    
    private func showAlert() {
        let alert = UIAlertController(title: "Current Room Not Selectable",
                                      message: "This room cannot be used as all participants are filled.",
                                      preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("OK", comment: "Default action"), style: .default))
        present(alert, animated: true)
    }
    
    private func showActionSheet(gameRoom: GameRoom) {
        let actionSheet = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        actionSheet.addAction(UIAlertAction(title: "Close this tab", style: .destructive))
        
        let awayTeamChoiceAction = teamChoiceAction(team: gameRoom.awayTeam)
        actionSheet.addAction(awayTeamChoiceAction)
        
        let homeTeamChoiceAction = teamChoiceAction(team: gameRoom.homeTeam)
        actionSheet.addAction(homeTeamChoiceAction)
        
        present(actionSheet, animated: true)
    }
    
    private func teamChoiceAction(team: Team) -> UIAlertAction {
        let teamChoiceAction = UIAlertAction(title: team.teamName, style: .default) { action in
            guard let teamName = action.title else { return }
            guard let teamData = SelectTeamName(teamName: teamName).encodeToJSONData() else { return }
            TeamSelectUseCase.requestRoomSelectResponse(from: TeamSelectUseCase.TeamSelectRequest(httpBody: teamData),
                                                        with: TeamSelectUseCase.TeamSelectTask(networkDispatcher: Mock())) { teamSelectResponse in
                                                            
            }
        }
        if team.userName != nil {
            teamChoiceAction.isEnabled = false
        }
        return teamChoiceAction
    }
    
    private func showGameTabBarController() {
        guard let gameTabBarController = storyboard?.instantiateViewController(withIdentifier: "GameTabBarController")
            else { return }
        gameTabBarController.modalPresentationStyle = .fullScreen
        present(gameTabBarController, animated: true)
    }
}

struct Mock: NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ()) {
        
    }
}
