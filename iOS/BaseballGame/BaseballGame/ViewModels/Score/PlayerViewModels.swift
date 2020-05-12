//
//  PlayerViewModels.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class PlayerViewModels {
    private let playerViewModels: [PlayerInfoViewModel]
    
    init(playerInfos: [PlayerInfo]) {
        self.playerViewModels = playerInfos.map { PlayerInfoViewModel(playerInfo: $0)}
    }
}
