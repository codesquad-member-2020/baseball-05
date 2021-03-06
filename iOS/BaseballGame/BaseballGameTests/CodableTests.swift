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
        guard let jsonData = Data.jsonData(forResource: "TeamInfoSuccessStub") else { return }
        let gameInfo = try? JSONDecoder().decode(GameRoom.self, from: jsonData)
        XCTAssertNotNil(gameInfo)
    }
    
    func testRoundsSuccess() {
        guard let jsonData = Data.jsonData(forResource: "RoundsSuccessStub") else { return }
        let roundsResponse = try? JSONDecoder().decode(PlayDataResponse.self, from: jsonData)
        XCTAssertNotNil(roundsResponse)
    }
}
