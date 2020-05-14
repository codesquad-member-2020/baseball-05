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
        let gameInfoRequest = GameRoomUseCase.GameRoomRequest(path: "mock")
        let mockGameInfoSuccess = MockGameRoomsSuccess()
        mockGameInfoSuccess.execute(request: gameInfoRequest) { data, urlResponse, error in
            XCTAssertNotNil(data)
        }
        
        let gameInfoTask = GameRoomUseCase.GameRoomTask(networkDispatcher: mockGameInfoSuccess)
        gameInfoTask.perform(gameInfoRequest) { gameInfo in
            XCTAssertNotNil(gameInfo)
        }
        
        GameRoomUseCase.requestGameRoom(from: gameInfoRequest,
                                        with: gameInfoTask) { gameInfo in
                                            XCTAssertNotNil(gameInfo)
        }
        
        GameRoomUseCase.requestGameRoom(from: GameRoomUseCase.GameRoomRequest(path: "mock"),
                                        with: GameRoomUseCase.GameRoomTask(networkDispatcher: MockGameRoomsSuccess())) {
                        XCTAssertNotNil($0)
        }
    }
}


