//
//  SelectTeamName.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import Foundation

struct SelectingTeam: Codable {
    let teamName: String
    let userId: String
    
    func encodeToJSONData() -> Data? {
        return try? JSONEncoder().encode(self)
    }
}
