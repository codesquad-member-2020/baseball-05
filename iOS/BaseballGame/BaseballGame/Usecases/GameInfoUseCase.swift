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
    
    final class GameInfoTask: NetworkTask {
        typealias Output = [GameInfo]
        
        private let networkDispatcher: NetworkDispatcher
        
        init(networkDispatcher: NetworkDispatcher) {
            self.networkDispatcher = networkDispatcher
        }
        
        func perform(_ request: Request, completionHandler: @escaping (Output?) -> ()) {
            networkDispatcher.execute(request: request) { data, urlResponse, error in
                guard error == nil, let data = data else { return }
                let output = try? JSONDecoder().decode(Output.self, from: data)
                completionHandler(output)
            }
        }
    }
    
    static func requestGameInfo(from gameInfoRequest: GameInfoRequest, with gameInfoTask: GameInfoTask,
                                completionHandler: @escaping ([GameInfo]?) -> ()) {
        gameInfoTask.perform(gameInfoRequest) { gameInfo in
            completionHandler(gameInfo)
        }
    }
}
