//
//  MockRoomIsFullFail.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct MockRoomIsFullFail: NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ()) {
        guard let jsonData = Data.jsonData(forResource: "RoomIsFullFailStub") else { return }
        completionHandler(jsonData, nil, nil)
    }
}
