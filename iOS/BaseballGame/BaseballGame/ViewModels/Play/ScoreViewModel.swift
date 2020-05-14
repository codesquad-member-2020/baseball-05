//
//  ScoreViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class ScoreViewModel {
    enum Notification {
        static let scoreDataDidChange = Foundation.Notification.Name("scoreDataDidChange")
    }
    
    let awayTeamName: String
    var awayScore: Int
    let homeTeamName: String
    var homeScore: Int
    
    init(awayTeamName: String, awayScore: Int, homeTeamName: String, homeScore: Int) {
        self.awayTeamName = awayTeamName
        self.awayScore = awayScore
        self.homeTeamName = homeTeamName
        self.homeScore = homeScore
        NotificationCenter.default.post(name: Notification.scoreDataDidChange, object: self)
    }
}
