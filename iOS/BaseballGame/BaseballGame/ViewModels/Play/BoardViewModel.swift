//
//  BoardViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class BoardViewModel {
    enum Notification {
        static let boardModelsDidChange = Foundation.Notification.Name("boardModelsDidChange")
    }
    
    let sboViewModel: SBOsViewModel
    let inningViewModel: UserInningViewModel
    
    init(sboViewModel: SBOsViewModel, inningViewModel: UserInningViewModel) {
        self.sboViewModel = sboViewModel
        self.inningViewModel = inningViewModel
        NotificationCenter.default.post(name: Notification.boardModelsDidChange, object: self)
    }
}

final class SBOsViewModel: ViewModelBinding {
    typealias Key = SBO
    let sbo: SBO
    
    init(sbo: SBO) {
        self.sbo = sbo
    }
}

final class UserInningViewModel: ViewModelBinding {
    typealias Key = UserInning
    let userInning: UserInning
    
    init(userInning: UserInning) {
        self.userInning = userInning
    }
    
    func offenseOrDefenseText() -> String? {
        guard let isOffense = isOffense else { return nil }
        if isOffense {
            return "공격"
        } else {
            return "수비"
        }
    }
    
    var isOffense: Bool? {
        guard let kind = userInning.kind else { return nil }
        switch kind {
        case .away:
            switch userInning.half {
            case .top:
                return true
            case .bottom:
                return false
            }
        case .home:
            switch userInning.half {
            case .top:
                return false
            case .bottom:
                return true
            }
        }
    }
}
