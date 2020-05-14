//
//  MockGameInfoSuccess.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct MockGameRoomsSuccess: NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ()) {
        let jsonData = Data.jsonData(forResource: "GameRoomsSuccessStub")
        completionHandler(jsonData, nil, nil)
    }
}
