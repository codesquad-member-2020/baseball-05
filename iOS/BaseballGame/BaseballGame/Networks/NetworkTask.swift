//
//  Task.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

final class NetworkTask<Output: Codable> {
    private let networkDispatcher: NetworkDispatcher
    
    init(networkDispatcher: NetworkDispatcher) {
        self.networkDispatcher = networkDispatcher
    }
    
    func perform(_ request: Request, completionHandler: (Output?) -> ()) {
        networkDispatcher.execute(request: request) { data, urlResponse, error in
            guard error == nil, let data = data else { return }
            guard let output = try? JSONDecoder().decode(Output.self, from: data) else { return }
            completionHandler(output)
        }
    }
}


