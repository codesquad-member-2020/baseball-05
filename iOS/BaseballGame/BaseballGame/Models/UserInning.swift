//
//  UserInninf.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct UserInning {
    let inningCount: Int
    let half: Half
    var kind: TeamKind?
    
    init(inning: Inning, kind: TeamKind?) {
        self.inningCount = inning.id
        self.half = inning.half
        self.kind = kind
    }
}
