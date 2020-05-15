//
//  RoundsUseCase.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct PlayUseCase {
    struct PlayDataRequest: Request {
        var path: String {
            return "http://15.165.69.44:8080/mock/games/rounds?matchesId=\(matchID)"
        }
        private let matchID: Int
        
        init(matchID: Int) {
            self.matchID = matchID
        }
    }
    
    struct PlayDataTask: NetworkTask {
        typealias Output = PlayDataResponse
        
        private let networkDispatcher: NetworkDispatcher
        
        init(networkDispatcher: NetworkDispatcher) {
            self.networkDispatcher = networkDispatcher
        }
        
        func perform(_ request: Request, completionHandler: @escaping (PlayDataResponse?) -> ()) {
            networkDispatcher.execute(request: request) { data, urlResponse, error in
                guard error == nil, let data = data else { return }
                let output = try? JSONDecoder().decode(Output.self, from: data)
                completionHandler(output)
            }
        }
    }
    
    static func reqeustPlayData(from playDataRequest: PlayDataRequest, with playDataTask: PlayDataTask, completionHandler: @escaping (PlayDataResponse?) -> ()) {
        playDataTask.perform(playDataRequest) { playDataResponse in
            completionHandler(playDataResponse)
        }
    }
}
