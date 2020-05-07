//
//  ViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomViewController: UIViewController {
    //MARK:- Internal properties
    private var gameRoomViewModels: GameRoomViewModels!
    private var gameRoomCollectionView: GameRoomCollectionView!
    private let gameRoomTitleLabel = TitleLabel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureGameTitleLabel()
        configureCollectionView()
        configureObserver()
        configureUseCase()
    }
    
    private func configureGameTitleLabel() {
        view.addSubview(gameRoomTitleLabel)
        
        gameRoomTitleLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        gameRoomTitleLabel.topAnchor.constraint(equalTo: safeArea.topAnchor,
                                                constant: 26).isActive = true
    }
    
    private func configureCollectionView() {
        gameRoomCollectionView = GameRoomCollectionView(collectionViewLayout:
            GameRoomCollectionViewFlowLayout(superFrame: view.frame))
        gameRoomCollectionView.register(GameRoomCell.self, forCellWithReuseIdentifier: GameRoomCell.reuseIdentifier)
        gameRoomCollectionView.dataSource = self
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
                                        with: GameRoomUseCase.GameRoomTask(networkDispatcher: NetworkManager()))
        { gameRooms in
            guard let gameRooms = gameRooms else { return }
            self.gameRoomViewModels = GameRoomViewModels(gameViewModels:
                gameRooms.map { GameRoomViewModel(gameRoom: $0)})
        }
    }
}

extension GameRoomViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        guard let gameRoomViewModels = gameRoomViewModels else { return 0 }
        return gameRoomViewModels.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let gameRoomCell = collectionView.dequeueReusableCell(withReuseIdentifier: GameRoomCell.reuseIdentifier,
                                                                    for: indexPath) as? GameRoomCell
            else { return GameRoomCell() }
        
        guard let gameRoomViewModel = gameRoomViewModels.gameViewModel(at: indexPath.item)
            else { return GameRoomCell() }
        gameRoomCell.configure(gameRoom: gameRoomViewModel.gameRoom)
        return gameRoomCell
    }
}
