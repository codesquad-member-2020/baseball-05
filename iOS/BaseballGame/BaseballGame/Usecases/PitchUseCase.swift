//
//  PitchUseCase.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/15.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct PitchUseCase {
    struct PitchRequest: Request {
        var path: String {
            return "http://15.165.69.44:8080/mock/games/rounds?matchesId=\(matchID)"
        }
        var httpMethod: HTTPMethod {
            return .post
        }
        private let matchID: Int
        
        
        init(matchID: Int) {
            self.matchID = matchID
        }
    }
    
    struct PitchTask: NetworkTask {
        typealias Output = ResultResponse
        
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
    
    static func requestPitch(from pitchRequest: PitchRequest, with pitchTask: PitchTask, completionHandler: @escaping (ResultResponse?) -> ()) {
        pitchTask.perform(pitchRequest) { resultResponse in
            completionHandler(resultResponse)
        }
    }
}
