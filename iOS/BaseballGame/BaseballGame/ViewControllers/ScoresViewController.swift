//
//  ScoresViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class ScoresViewController: UIViewController {
    @IBOutlet weak var titleView: TitleView!
    @IBOutlet weak var playerInfoTable: UITableView!
    private let playerViewModels = PlayerInfoViewModels(playerInfos: [PlayerInfo(player: "류현진",
                                                                                 mounts: 1,
                                                                                 hits: 0,
                                                                                 outs: 1,
                                                                                 average: 1.0)])
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
        configurePlayerInfoTableDataSource()
    }
    
    private func configureTitleView() {
        titleView.delegate = self
    }
    
    private func configurePlayerInfoTableDataSource() {
        playerInfoTable.dataSource = playerViewModels
    }
}

extension ScoresViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true)
    }
}
