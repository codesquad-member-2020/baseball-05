//
//  CurrentPlayerViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class CurrentPlayerViewModel: ViewModelBinding {
    typealias Key = CurrentPlayer
    let currentPlayer: CurrentPlayer
    
    init(currentPlayer: CurrentPlayer) {
        self.currentPlayer = currentPlayer
    }
}

