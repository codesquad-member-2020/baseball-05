//
//  ScoresViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class ScoresViewController: UIViewController {
    @IBOutlet weak var titleView: GameHeaderView!
    @IBOutlet weak var playerInfoTable: UITableView!
    @IBOutlet weak var inningTitleStack: InningTitleStack!
    @IBOutlet weak var awayTeamStack: TeamStackView!
    @IBOutlet weak var homeTeamStack: TeamStackView!
    
    private let playerInfoViewModels = PlayerInfoViewModels(awayTeamPlayerInfos: [PlayerInfo(player: "DUMMY_AWAY",
                                                                                             mounts: 1,
                                                                                             hits: 0,
                                                                                             outs: 1,
                                                                                             average: 1.0)],
                                                            homeTeamPlayerInfos: [PlayerInfo(player: "DUMMY_HOME",
                                                                                             mounts: 1,
                                                                                             hits: 0,
                                                                                             outs: 1,
                                                                                             average: 1.0)])
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
        configurePlayerInfoTableDataSource()
        configureObserver()
    }
    
    private func configureTitleView() {
        titleView.delegate = self
    }
    
    private func configurePlayerInfoTableDataSource() {
        playerInfoTable.dataSource = playerInfoViewModels
    }
    
    @IBAction func teamSegmentDidTouch(_ sender: UISegmentedControl) {
        playerInfoViewModels.togglePlayerInfoViewModel()
    }
    
    private func configureObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(updateTableView),
                                               name: PlayerInfoViewModels.Notification.currentTeamPlayerViewModelsDidChange,
                                               object: playerInfoViewModels)
    }
    
    @objc private func updateTableView() {
        DispatchQueue.main.async {
            self.playerInfoTable.reloadData()
        }
    }
}

extension ScoresViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true)
    }
}
