//
//  PlayViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayTablesViewModel: NSObject {
    enum Notification {
        static let tablesModelDidChange = Foundation.Notification.Name("tablesModelDidChange")
    }
    
    private let currentPlayerViewModels: CurrentPlayerViewModels
    private let roundInfoViewModels: RoundInfoViewModels
    private let isOffense: Bool
    private weak var currentPlayerTableView: UITableView?
    private weak var roundInfoTableView: UITableView?
    
    init(currentPlayers: [CurrentPlayer],
         rounds: [Round],
         currentPlayerTableView: UITableView,
         roundInfoTableView: UITableView,
         isOffense: Bool) {
        self.currentPlayerViewModels = CurrentPlayerViewModels(currentPlayers: currentPlayers)
        self.roundInfoViewModels = RoundInfoViewModels(rounds: rounds)
        self.currentPlayerTableView = currentPlayerTableView
        self.roundInfoTableView = roundInfoTableView
        self.isOffense = isOffense
        super.init()
        NotificationCenter.default.post(name: Notification.tablesModelDidChange, object: self)
    }
}

extension PlayTablesViewModel: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if tableView === currentPlayerTableView {
            return currentPlayerViewModels.count
        } else if tableView === roundInfoTableView {
            return roundInfoViewModels.count
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if tableView === currentPlayerTableView {
            guard let currentPlayerCell = tableView.dequeueReusableCell(withIdentifier:
                CurrentPlayerCell.identifier) as? CurrentPlayerCell else { return CurrentPlayerCell() }
            guard let currentPlayerViewModel = currentPlayerViewModels.viewModel(at: indexPath.row)
                else { return CurrentPlayerCell() }
            
            currentPlayerCell.configure(currentPlayer: currentPlayerViewModel.currentPlayer, by: isOffense)
            return currentPlayerCell
        } else if tableView === roundInfoTableView {
            guard let roundInfoCell = tableView.dequeueReusableCell(withIdentifier:
                RoundInfoCell.identifier) as? RoundInfoCell else { return RoundInfoCell() }
            guard let roundInfoViewModel = roundInfoViewModels.viewModel(at: indexPath.row)
                else { return RoundInfoCell()}
            roundInfoCell.configure(orderText: String(indexPath.row + 1),
                                    roundInfo: roundInfoViewModel.round)
            return roundInfoCell
        }
        return UITableViewCell()
    }
}
