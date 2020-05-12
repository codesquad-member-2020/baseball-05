//
//  PlayerViewModels.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayerInfoViewModels: NSObject {
    enum Notification {
        static let currentTeamPlayerViewModelsDidChange = Foundation.Notification.Name("currentTeamPlayerViewModelsDidChange")
    }
    private let awayTeamPlayerViewModels: [PlayerInfoViewModel]
    private let homeTeamPlayerViewModels: [PlayerInfoViewModel]
    private var currentTeamPlayerViewModels: [PlayerInfoViewModel]
    private var isAwayTeam = false
    
    init(awayTeamPlayerInfos: [PlayerInfo], homeTeamPlayerInfos: [PlayerInfo]) {
        self.awayTeamPlayerViewModels = awayTeamPlayerInfos.map { PlayerInfoViewModel(playerInfo: $0)}
        self.homeTeamPlayerViewModels = homeTeamPlayerInfos.map { PlayerInfoViewModel(playerInfo: $0)}
        self.currentTeamPlayerViewModels = self.awayTeamPlayerViewModels
        self.isAwayTeam = true
        super.init()
        NotificationCenter.default.post(name: Notification.currentTeamPlayerViewModelsDidChange,
                                        object: self)
    }
    
    private func playerInfoViewModel(at index: Int) -> PlayerInfoViewModel? {
        guard index < currentTeamPlayerViewModels.count else { return nil }
        return currentTeamPlayerViewModels[index]
    }
    
    func togglePlayerInfoViewModel() {
        isAwayTeam = !isAwayTeam
        changeCurrentViewModel()
    }
    
    func changeCurrentViewModel() {
        currentTeamPlayerViewModels = isAwayTeam ? awayTeamPlayerViewModels : homeTeamPlayerViewModels
        NotificationCenter.default.post(name: Notification.currentTeamPlayerViewModelsDidChange,
                                        object: self)
    }
}

extension PlayerInfoViewModels: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return currentTeamPlayerViewModels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let playerInfoCell = tableView.dequeueReusableCell(withIdentifier: PlayerInfoCell.identifier) as? PlayerInfoCell
            else { return PlayerInfoCell() }
        guard let playerInfoViewModel = playerInfoViewModel(at: indexPath.row) else { return PlayerInfoCell() }
        
        playerInfoCell.configure(playerInfo: playerInfoViewModel.playerInfo)
        return playerInfoCell
    }
}
