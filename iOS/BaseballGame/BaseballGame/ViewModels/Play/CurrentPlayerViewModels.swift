//
//  CurrentPlayerViewModels.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class CurrentPlayerViewModels {
    private let viewModels: [CurrentPlayerViewModel]
    
    init(currentPlayers: [CurrentPlayer]) {
        self.viewModels = currentPlayers.map { CurrentPlayerViewModel(currentPlayer: $0) }
    }
    
    var count: Int {
        return viewModels.count
    }
    
    func viewModel(at index: Int) -> CurrentPlayerViewModel? {
        guard index < viewModels.count else { return nil }
        return viewModels[index]
    }
}
