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
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
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
                                        with: GameRoomUseCase.GameRoomTask(networkDispatcher: MockGameRoomsSuccess()))
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
            showAlertRoomNotSelectable()
        }
    }
    
    private func showAlertRoomNotSelectable() {
        let alert = UIAlertController(title: "Current Room Not Selectable",
                                      message: "This room cannot be used as all participants are filled.",
                                      preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("OK", comment: "Default action"), style: .default))
        present(alert, animated: true)
    }
    
    private func showActionSheet(gameRoom: GameRoom) {
        let actionSheet = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        actionSheet.addAction(UIAlertAction(title: "Close this tab", style: .destructive))
        
        let awayTeamChoiceAction = teamChoiceAction(team: gameRoom.awayTeam) { result in
            guard let result = result else { return }
            if result { self.requestSelectedRoomIsFullRecursively(roomID: gameRoom.id, kind: .away) }
        }
        actionSheet.addAction(awayTeamChoiceAction)
        
        let homeTeamChoiceAction = teamChoiceAction(team: gameRoom.homeTeam) { result in
            guard let result = result else { return }
            if result { self.requestSelectedRoomIsFullRecursively(roomID: gameRoom.id, kind: .home) }
        }
        actionSheet.addAction(homeTeamChoiceAction)
        
        present(actionSheet, animated: true)
    }
    
    private func requestSelectedRoomIsFullRecursively(roomID: Int, kind: TeamKind) {
        RoomIsFullUseCase.requestResultResponse(from: RoomIsFullUseCase.RoomIsFullRequest(roomID: roomID),
                                                with: RoomIsFullUseCase.RoomIsFullTask(networkDispatcher: MockRoomIsFullSuccess()))
        { status in
            guard let status = status else { return }
            if status == .success {
                self.showGameTabBarController(roomID: roomID, kind: kind)
            } else {
                DispatchQueue(label: "reqeustRoomIsFull").asyncAfter(deadline: .now() + 1) {
                    self.requestSelectedRoomIsFullRecursively(roomID: roomID, kind: kind)
                }
            }
        }
    }
    
    private func showGameTabBarController(roomID: Int, kind: TeamKind) {
        guard let gameTabBarController = storyboard?.instantiateViewController(withIdentifier: "GameTabBarController")
            else { return }
        gameTabBarController.modalPresentationStyle = .fullScreen
        guard let playViewController = gameTabBarController.children.first as? PlayViewController else { return }
        playViewController.roomID = roomID
        playViewController.userTeamKind = kind
        present(gameTabBarController, animated: true)
    }
    
    private func teamChoiceAction(team: Team, resultHandler: @escaping (Bool?) ->()) -> UIAlertAction {
        let teamChoiceAction = UIAlertAction(title: team.teamName, style: .default) { action in
            guard let teamName = action.title else { return }
            TeamSelectingUseCase.requestRoomSelectResponse(from: TeamSelectingUseCase.TeamSelectingRequest(teamName: teamName),
                                                           with: TeamSelectingUseCase.TeamSelectingTask(networkDispatcher: MockTeamSelectSuccess()))
            { status in
                guard let status = status else { return }
                if status == .fail {
                    self.showAlertTeamNotSelectable()
                } else {
                    resultHandler(true)
                }
            }
        }
        if team.userName != nil {
            teamChoiceAction.isEnabled = false
        }
        return teamChoiceAction
    }
    
    private func showAlertTeamNotSelectable() {
        let alert = UIAlertController(title: "Current Team Not Selectable",
                                      message: "Another user has already selected.",
                                      preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("OK", comment: "Default action"), style: .default))
        present(alert, animated: true)
    }
}

