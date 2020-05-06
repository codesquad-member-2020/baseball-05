//
//  JsonDataTests.swift
//  BaseballGameTests
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import XCTest
@testable import BaseballGame

final class JsonDataTests: XCTestCase {
    func testTeamListSuccessStubJsonFileSuccess() {
        let jsonData = Data.jsonData(forResource: "TeamListSuccessStub")
        XCTAssertNotNil(jsonData)
    }
}
