//
//  PlayerViewModels.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayerInfoViewModels: NSObject {
    private let playerViewModels: [PlayerInfoViewModel]
    
    init(playerInfos: [PlayerInfo]) {
        self.playerViewModels = playerInfos.map { PlayerInfoViewModel(playerInfo: $0)}
    }
    
    private func playerInfoViewModel(at index: Int) -> PlayerInfoViewModel? {
        guard index < playerViewModels.count else { return nil }
        return playerViewModels[index]
    }
}

extension PlayerInfoViewModels: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return playerViewModels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let playerInfoCell = tableView.dequeueReusableCell(withIdentifier: PlayerInfoCell.identifier) as? PlayerInfoCell
            else { return PlayerInfoCell() }
        guard let playerInfoViewModel = playerInfoViewModel(at: indexPath.row) else { return PlayerInfoCell() }
        
        playerInfoCell.configure(playerInfo: playerInfoViewModel.playerInfo)
        return playerInfoCell
    }
}
