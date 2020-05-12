//
//  BatterInfoViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class RoundInfoViewModel: ViewModelBinding {
    typealias Key = RoundInfo
    let roundInfo: RoundInfo
    
    init(roundInfo: RoundInfo) {
        self.roundInfo = roundInfo
    }
}
