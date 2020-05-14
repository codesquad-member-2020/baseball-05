//
//  FullUseCase.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct RoomIsFullUseCase {
    struct RoomIsFullRequest: Request {
        var path: String {
            return "http://15.165.69.44:8080/api/start/\(roomID)"
        }
        var httpMethod: HTTPMethod {
            return .post
        }
        var roomID: Int
        
        init(roomID: Int) {
            self.roomID = roomID
        }
    }
    
    struct RoomIsFullTask: NetworkTask {
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
    
    static func requestResultResponse(from roomIsFullRequest: RoomIsFullRequest,
                                      with roomIsFullTask: RoomIsFullTask,
                                      completionHandler: @escaping (Status?) -> ()) {
        roomIsFullTask.perform(roomIsFullRequest) { resultResponse in
            completionHandler(resultResponse?.result)
        }
    }
}
