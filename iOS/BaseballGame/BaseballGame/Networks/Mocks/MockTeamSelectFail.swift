//
//  MockTeamSelectFail.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct MockTeamSelectFail: NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ()) {
        let jsonData = Data.jsonData(forResource: "TeamSelectFailStub")
        completionHandler(jsonData, nil, nil)
    }
}
