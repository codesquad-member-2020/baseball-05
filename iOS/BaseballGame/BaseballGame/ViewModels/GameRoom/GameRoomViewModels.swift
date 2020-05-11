//
//  GameListViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomViewModels: NSObject {
    enum Notification {
        static let gameViewModelsDidChange = Foundation.Notification.Name("gameViewModelsDidChange")
    }
    
    static let titleText = "게임 목록"
    static let titleColor = UIColor.black
    static let gameRoomDefaultText = "GAME"
    
    private let gameRoomViewModels: [GameRoomViewModel]
    
    init(with gameRooms: [GameRoom]) {
        self.gameRoomViewModels = gameRooms.map { GameRoomViewModel(gameRoom: $0) }
        super.init()
        NotificationCenter.default.post(name: Notification.gameViewModelsDidChange,
                                        object: self)
    }
    
    func gameViewModel(at index: Int) -> GameRoomViewModel? {
        guard index < gameRoomViewModels.count else { return nil}
        return gameRoomViewModels[index]
    }
    
    var count: Int {
        return gameRoomViewModels.count
    }
}

extension GameRoomViewModels: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return gameRoomViewModels.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let gameRoomCell = collectionView.dequeueReusableCell(withReuseIdentifier: GameRoomCell.identifier,
                                                                    for: indexPath) as? GameRoomCell
            else { return GameRoomCell() }
        
        let gameRoomViewModel = gameRoomViewModels[indexPath.item]
        gameRoomCell.configure(gameRoom: gameRoomViewModel.gameRoom)
        return gameRoomCell
    }
}
