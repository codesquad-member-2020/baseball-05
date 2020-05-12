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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
    }
    
    private func configureTitleView() {
        titleView.delegate = self
    }
}

extension ScoresViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true)
    }
}
