//
//  GameViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class GameRoomViewModel: ViewModelBinding {
    typealias Key = GameRoom
    let gameRoom: Key
    
    init(gameRoom: Key) {
        self.gameRoom = gameRoom
    }
}
