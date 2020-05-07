//
//  NetworkDispatcher.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

protocol NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ())
}

struct NetworkManager: NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ()) {
        guard let urlRequest = request.urlRequest() else { return }
        URLSession.shared.dataTask(with: urlRequest) { data, urlResponse, error in
            completionHandler(data, urlResponse, error)
        }.resume()
    }
}
