//
//  CodableTests.swift
//  BaseballGameTests
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import XCTest
@testable import BaseballGame

final class CodableTests: XCTestCase {
    func testGameInfoModelSuccess() {
        guard let jsonData = Data.jsonData(forResource: "TeamListSuccessStub") else { return }
        guard let gameInfo = try? JSONDecoder().decode(GameInfo.self, from: jsonData) else { return }
        XCTAssertNotNil(gameInfo)
    }
}
