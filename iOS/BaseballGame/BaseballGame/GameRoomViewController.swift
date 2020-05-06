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
    private let gameRoomTitleLabel: TitleLabel = {
        let label = TitleLabel()
        label.text = GameRoomViewModel.titleText
        label.textColor = GameRoomViewModel.titleColor
        return label
    }()
    private let gameRoomStackView = GameRoomStackView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureGameListLabel()
        configureGameInfoStackView()
        configureUseCase()
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
        GameRoomUseCase.requestGameRoom(from: GameRoomUseCase.GameRoomRequest(path: "mock"),
                                        with: GameRoomUseCase.GameRoomTask(networkDispatcher: MockGameRoomSuccess()))
        { gameRooms in
            guard let gameRooms = gameRooms else { return }
            gameRooms.forEach {
                let gameRoomView = GameRoomView()
                gameRoomView.configure(gameRoom: $0)
                self.gameRoomStackView.add(gameRoomView: gameRoomView)
            }
        }
    }
}
