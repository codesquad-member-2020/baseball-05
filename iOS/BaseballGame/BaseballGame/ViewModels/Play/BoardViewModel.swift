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
    let inningViewModel: InningViewModel
    
    init(sboViewModel: SBOsViewModel, inningViewModel: InningViewModel) {
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

final class InningViewModel: ViewModelBinding {
    typealias Key = Inning
    let inning: Inning
    
    init(inning: Inning) {
        self.inning = inning
    }
}
