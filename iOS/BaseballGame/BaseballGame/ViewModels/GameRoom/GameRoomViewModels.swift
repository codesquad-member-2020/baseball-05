//
//  GameListViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomViewModels {
    enum Notification {
        static let gameViewModelsDidChange = Foundation.Notification.Name("gameViewModelsDidChange")
    }
    
    static let titleText = "게임 목록"
    static let titleColor = UIColor.black
    static let gameRoomDefaultText = "GAME"
    
    private let gameViewModels: [GameRoomViewModel]
    
    init(gameViewModels: [GameRoomViewModel]) {
        self.gameViewModels = gameViewModels
        NotificationCenter.default.post(name: Notification.gameViewModelsDidChange,
                                        object: self)
    }
    
    func gameViewModel(at index: Int) -> GameRoomViewModel? {
        guard index < gameViewModels.count else { return nil}
        return gameViewModels[index]
    }
    
    var count: Int {
        return gameViewModels.count
    }
}
