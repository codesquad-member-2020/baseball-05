//
//  ViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomViewController: UICollectionViewController {
    //MARK:- Internal properties
    private let gameRoomTitleLabel: TitleLabel = {
        let label = TitleLabel()
        label.text = GameRoomViewModels.titleText
        label.textColor = GameRoomViewModels.titleColor
        return label
    }()
    private let gameRoomStackView = GameRoomStackView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCollectionView()
        configureGameListLabel()
        configureGameInfoStackView()
        configureUseCase()
    }
    
    private func configureCollectionView() {
        collectionView.backgroundColor = .black
    }
    
    private func configureGameListLabel() {
        view.addSubview(gameRoomTitleLabel)
        
        gameRoomTitleLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        gameRoomTitleLabel.topAnchor.constraint(equalTo: safeArea.topAnchor,
                                                constant: 26).isActive = true
    }
    
    private func configureGameInfoStackView() {
        view.addSubview(gameRoomStackView)
        gameRoomStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor,
                                                   constant: 10).isActive = true
        gameRoomStackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10).isActive = true
        gameRoomStackView.topAnchor.constraint(equalTo: gameRoomTitleLabel.bottomAnchor, constant: 43).isActive = true
    }
    
    private func configureUseCase() {
        GameRoomUseCase.requestGameRoom(from: GameRoomUseCase.GameRoomRequest(),
                                        with: GameRoomUseCase.GameRoomTask(networkDispatcher: NetworkManager()))
        { gameRooms in
            guard let gameRooms = gameRooms else { return }
            let gameViewModels = GameRoomViewModels(gameViewModels: gameRooms.map { GameRoomViewModel(gameRoom: $0)})
            gameViewModels.repeatGameRoomViewModels { gameViewModel in
                DispatchQueue.main.async {
                    let gameRoomView = GameRoomView()
                    gameRoomView.configure(gameRoom: gameViewModel.gameRoom)
                    self.gameRoomStackView.add(gameRoomView: gameRoomView)
                }
            }
        }
    }
}
