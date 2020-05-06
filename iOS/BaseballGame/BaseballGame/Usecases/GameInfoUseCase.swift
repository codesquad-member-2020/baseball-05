//
//  GameInfoUseCase.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct GameInfoUseCase {
    struct GameInfoRequest: Request {
        var path: String
    }
    
    static func requestGameInfo(from gameInfoRequest: GameInfoRequest, with gameInfoTask: NetworkTask<GameInfo>,
                                completionHandler: @escaping (GameInfo?) -> ()) {
        gameInfoTask.perform(gameInfoRequest) { gameInfo in
            guard let gameInfo = gameInfo else { return }
            completionHandler(gameInfo)
        }
    }
}
