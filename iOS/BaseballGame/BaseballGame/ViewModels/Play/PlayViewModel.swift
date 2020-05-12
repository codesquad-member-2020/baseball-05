//
//  PlayViewModel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayViewModel: NSObject {
    private let currentPlayerViewModels: CurrentPlayerViewModels
    private let roundInfoViewModels: RoundInfoViewModels
    private weak var currentPlayerTableView: UITableView?
    private weak var roundInfoTableView: UITableView?
    
    init(currentPlayers: [CurrentPlayer],
         roundInfos: [RoundInfo],
         currentPlayerTableView: UITableView,
         roundInfoTableView: UITableView) {
        self.currentPlayerViewModels = CurrentPlayerViewModels(currentPlayers: currentPlayers)
        self.roundInfoViewModels = RoundInfoViewModels(roundInfos: roundInfos)
        self.currentPlayerTableView = currentPlayerTableView
        self.roundInfoTableView = roundInfoTableView
    }
}

extension PlayViewModel: UITableViewDataSource {
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
            
            currentPlayerCell.configure(currentPlayer: currentPlayerViewModel.currentPlayer)
            return currentPlayerCell
        } else if tableView === roundInfoTableView {
            guard let roundInfoCell = tableView.dequeueReusableCell(withIdentifier:
                RoundInfoCell.identifier) as? RoundInfoCell else { return RoundInfoCell() }
            return roundInfoCell
        }
        return UITableViewCell()
    }
}
