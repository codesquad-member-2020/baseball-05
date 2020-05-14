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
    
    init(sboViewModel: SBOsViewModel) {
        self.sboViewModel = sboViewModel
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

