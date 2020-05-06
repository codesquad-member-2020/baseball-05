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
    
    static func requestGameInfo(from path: String, with networkDispatcher: NetworkDispatcher,
                                completionHandler: @escaping (GameInfo?) -> ()) {
        let gameInfoTask = NetworkTask<GameInfo>(networkDispatcher: networkDispatcher)
        let gameInfoRequest = GameInfoRequest(path: path)
        gameInfoTask.perform(gameInfoRequest) { gameInfo in
            guard let gameInfo = gameInfo else { return }
            completionHandler(gameInfo)
        }
    }
}
