//
//  Task.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

protocol NetworkTask {
    associatedtype Output
    
    func perform(_ request: Request, completionHandler: @escaping (Output?) -> ())
}
