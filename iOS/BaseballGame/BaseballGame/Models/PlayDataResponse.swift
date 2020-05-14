//
//  RoundsResponse.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct PlayDataResponse: Codable {
    let isFirstHalf: Bool
    let homeTeam: PlayingTeam
    let awayTeam: PlayingTeam
    let currentPlayers: CurrentPlayers
    let inning: Inning
    let plates: [Plate]
}

struct PlayingTeam: Codable {
    let name: String
    let score: Int
    let isOffense: Bool
}

struct CurrentPlayers: Codable {
    let pitcher: Pitcher
    let batter: Batter
}

struct Pitcher: Codable {
    let name: String
    let pitches: Int
}

struct Batter: Codable {
    let name: String
    let mounts: Int
    let hits: Int
}

struct Inning: Codable {
    let id: Int
    let half: Half
}

enum Half: String, Codable {
    case top = "초"
    case bottom = "말"
}

struct Plate: Codable {
    let id: Int
    let outs: Int
    let batter: Batter
    let rounds: [Round]
}

struct Round: Codable {
    let decision: String
    let strike: Int
    let ball: Int
}
