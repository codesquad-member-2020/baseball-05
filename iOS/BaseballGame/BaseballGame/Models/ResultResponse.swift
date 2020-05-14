//
//  RoomSelectResponse.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct ResultResponse: Codable {
    let result: Status
}

enum Status: String, Codable {
    case success
    case fail
}
