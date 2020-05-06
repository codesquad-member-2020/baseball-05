//
//  NetworkDispatcher.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

protocol NetworkDispatcher {
    func execute(request: Request, completionHandler: (Data?, URLResponse?, Error?) -> ())
}
