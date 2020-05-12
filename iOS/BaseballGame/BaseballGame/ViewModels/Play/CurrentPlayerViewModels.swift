//
//  CurrentPlayerViewModels.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class CurrentPlayerViewModels {
    private let currentPlayerViewModels: [CurrentPlayerViewModel]
    
    init(currentPlayers: [CurrentPlayer]) {
        self.currentPlayerViewModels = currentPlayers.map { CurrentPlayerViewModel(currentPlayer: $0) }
    }
    
    var count: Int {
        return currentPlayerViewModels.count
    }
}
