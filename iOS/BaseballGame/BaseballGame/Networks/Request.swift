//
//  NetworkDispatcher.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

enum HTTPMethod: String, CustomStringConvertible {
    case get = "GET"
    case post = "POST"
    case patch = "PATCH"
    case delete = "DELETE"
    case put = "PUT"
    
    var description: String {
        return self.rawValue
    }
}

protocol Request {
    var path: String { get }
    var httpMethod: HTTPMethod { get }
    var httpBody: Data? { get }
    var format: String? { get }
    var httpHeaderFields: [String]? { get }
    
    func urlRequest() -> URLRequest?
}

enum NetworkErrorCase: Error {
    case invalidURL
    case notFound
}

extension Request {
    var httpMethod: HTTPMethod { return .get }
    var httpBody: Data? { return nil }
    var format: String? { return "application/json" }
    var httpHeaderFields: [String]? { return ["Content-Type", "Accept"] }
    
    func urlRequest() -> URLRequest? {
        guard let url = URL(string: path) else { return nil }
        let urlRequest: URLRequest = {
            var request = URLRequest(url: url)
            httpHeaderFields!.forEach {
                request.addValue(format!, forHTTPHeaderField: $0)
            }
            request.httpMethod = httpMethod.description
            request.httpBody = httpBody
            return request
        }()
        return urlRequest
    }
}
