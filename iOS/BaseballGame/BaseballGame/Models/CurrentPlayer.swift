//
//  PlayerPerInning.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

class CurrentPlayer {
    let name: String
    
    init(name: String) {
        self.name = name
    }
}

final class CurrentPitcher: CurrentPlayer {
    let pitches: Int
    
    init(name: String, pitches: Int) {
        self.pitches = pitches
        super.init(name: name)
    }
}

final class CurrentBatter: CurrentPlayer {
    let mounts: Int
    let hits: Int
    
    init(name: String, mounts: Int, hits: Int) {
        self.mounts = mounts
        self.hits = hits
        super.init(name: name)
    }
}


