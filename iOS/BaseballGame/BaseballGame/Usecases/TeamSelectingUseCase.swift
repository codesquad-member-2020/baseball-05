//
//  RoomSelectUseCase.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct TeamSelectingUseCase {
    struct TeamSelectingRequest: Request {
        var path: String {
            return "http://3.34.15.148/api/games"
        }
        var httpMethod: HTTPMethod {
            return .post
        }
        var httpBody: Data?
        
        init(teamName: String, userId: String = "solar") {
            self.httpBody = SelectingTeam(teamName: teamName,
                                          userId: userId).encodeToJSONData()
        }
    }
    
    struct TeamSelectingTask: NetworkTask {
        typealias Output = ResultResponse
        
        private let networkDispatcher: NetworkDispatcher
        
        init(networkDispatcher: NetworkDispatcher) {
            self.networkDispatcher = networkDispatcher
        }
        
        func perform(_ request: Request, completionHandler: @escaping (ResultResponse?) -> ()) {
            networkDispatcher.execute(request: request) { data, urlResponse, error in
                guard error == nil, let data = data else { return }
                let output = try? JSONDecoder().decode(Output.self, from: data)
                completionHandler(output)
            }
        }
    }
    
    static func requestRoomSelectResponse(from teamSelectRequest: TeamSelectingRequest,
                                          with teamSelectTask: TeamSelectingTask,
                                          completionHandler: @escaping (Status?) -> ()) {
        teamSelectTask.perform(teamSelectRequest) { teamSelectResponse in
            completionHandler(teamSelectResponse?.status)
        }
    }
}


