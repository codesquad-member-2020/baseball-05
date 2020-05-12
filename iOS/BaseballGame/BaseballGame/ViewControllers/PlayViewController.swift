//
//  PlayViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayViewController: UIViewController {
    @IBOutlet weak var titleView: TitleView!
    @IBOutlet weak var currentPlayerTable: UITableView!
    @IBOutlet weak var roundInfoTable: UITableView!
    private var playViewModel: PlayViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
        configurePlayViewModel()
    }
    
    private func configureTitleView() {
        titleView.delegate = self
    }
    
    private func configurePlayViewModel() {
        playViewModel = PlayViewModel(currentPlayers: [CurrentPlayer(name: "최동원",
                                                                     mounts: 39,
                                                                     hits: 0,
                                                                     isPitcher: true),
                                                       CurrentPlayer(name: "김광진",
                                                                     mounts: 1,
                                                                     hits: 0,
                                                                     isPitcher: false)],
                                      roundInfos: [RoundInfo(decision: "볼", strike: 1, ball: 1),
                                                   RoundInfo(decision: "볼", strike: 1, ball: 2),
                                                   RoundInfo(decision: "볼", strike: 1, ball: 3),
                                                   RoundInfo(decision: "스트라이크", strike: 2, ball: 3)],
                                      currentPlayerTableView: currentPlayerTable,
                                      roundInfoTableView: roundInfoTable)
        configureCurrentPlayerTableDataSource()
        configureRoundInfoTableDataSource()
    }
    
    private func configureCurrentPlayerTableDataSource() {
        currentPlayerTable.dataSource = playViewModel
    }
    
    private func configureRoundInfoTableDataSource() {
        roundInfoTable.dataSource = playViewModel
    }
}

extension PlayViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true)
    }
}
