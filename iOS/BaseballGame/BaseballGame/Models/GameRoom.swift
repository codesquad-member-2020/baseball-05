//
//  GameInfo.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct GameRoom: Codable {
    let id: Int
    let selectable: Bool
    let homeTeam: Team
    let awayTeam: Team
}

struct Team: Codable {
    let teamName: String
    let userName: String?
}
