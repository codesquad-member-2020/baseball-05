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
    
    init(roundInfos: [RoundInfo]) {
        self.roundInfoViewModels = roundInfos.map { RoundInfoViewModel(roundInfo: $0) }
    }
    
    var count: Int {
        return roundInfoViewModels.count
    }
}
