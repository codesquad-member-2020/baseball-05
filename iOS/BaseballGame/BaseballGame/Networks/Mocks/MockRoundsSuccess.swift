//
//  MockRoundsSuccess.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct MockRoundsSuccess: NetworkDispatcher {
    func execute(request: Request, completionHandler: @escaping (Data?, URLResponse?, Error?) -> ()) {
        guard let jsonData = Data.jsonData(forResource: "RoundsSuccessStub") else { return }
        completionHandler(jsonData, nil, nil)
    }
}
