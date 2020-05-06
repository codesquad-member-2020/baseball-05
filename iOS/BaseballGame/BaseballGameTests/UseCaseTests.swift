//
//  UseCaseTests.swift
//  BaseballGameTests
//
//  Created by kimdo2297 on 2020/05/06.
//  Copyright © 2020 Jason. All rights reserved.
//

import XCTest
@testable import BaseballGame

final class UseCaseTests: XCTestCase {
    func testGameInfoUseCaseSuccess() {
        let gameInfoRequest = GameInfoUseCase.GameInfoRequest(path: "mock")
        let mockGameInfoSuccess = MockGameInfoSuccess()
        mockGameInfoSuccess.execute(request: gameInfoRequest) { data, urlResponse, error in
            XCTAssertNotNil(data)
        }
        
        let gameInfoTask = GameInfoUseCase.GameInfoTask(networkDispatcher: mockGameInfoSuccess)
        gameInfoTask.perform(gameInfoRequest) { gameInfo in
            XCTAssertNotNil(gameInfo)
        }
        
        GameInfoUseCase.requestGameInfo(from: gameInfoRequest,
                                        with: gameInfoTask) { gameInfo in
                                            XCTAssertNotNil(gameInfo)
        }
        
        GameInfoUseCase.requestGameInfo(from: GameInfoUseCase.GameInfoRequest(path: "mock"),
                                        with: GameInfoUseCase.GameInfoTask(networkDispatcher: MockGameInfoSuccess())) {
                        XCTAssertNotNil($0)
        }
    }
}


