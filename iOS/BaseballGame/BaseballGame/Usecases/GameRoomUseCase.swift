//
//  GameInfoUseCase.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct GameRoomUseCase {
    struct GameRoomRequest: Request {
        var path: String {
            return "http://15.165.69.44:8080/mock/matches"
        }
    }
    
    struct GameRoomTask: NetworkTask {
        typealias Output = [GameRoom]
        
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
    
    static func requestGameRoom(from gameRoomRequest: GameRoomRequest, with gameRoomTask: GameRoomTask,
                                completionHandler: @escaping ([GameRoom]?) -> ()) {
        gameRoomTask.perform(gameRoomRequest) { gameRooms in
            completionHandler(gameRooms)
        }
    }
}
