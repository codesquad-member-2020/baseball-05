//
//  PlayerInfoViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class PlayerInfoViewModel: ViewModelBinding {
    typealias Key = PlayerInfo
    let playerInfo: Key
    
    init(playerInfo: PlayerInfo) {
        self.playerInfo = playerInfo
    }
}
