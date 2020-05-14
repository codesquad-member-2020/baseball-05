//
//  BatterInfoViewModels.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class RoundInfoViewModels {
    private let roundInfoViewModels: [RoundInfoViewModel]
    
    init(rounds: [Round]) {
        self.roundInfoViewModels = rounds.map { RoundInfoViewModel(round: $0) }.reversed()
    }
    
    var count: Int {
        return roundInfoViewModels.count
    }
    
    func viewModel(at index: Int) -> RoundInfoViewModel? {
        guard index < roundInfoViewModels.count else { return nil }
        return roundInfoViewModels[index]
    }
}
