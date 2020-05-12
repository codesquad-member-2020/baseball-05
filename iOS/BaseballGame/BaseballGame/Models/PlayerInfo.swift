//
//  PlayerInfo.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct PlayerInfoResponse: Codable {
    let player: String
    let mounts: Int
    let hits: Int
    let outs: Int
}

struct PlayerInfo {
    let player: String
    let mounts: Int
    let hits: Int
    let outs: Int
    let average: Double
}
