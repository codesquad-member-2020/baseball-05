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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
    }
    
    private func configureTitleView() {
        titleView.delegate = self
    }
}

extension PlayViewController: TitleViewDelegate {
    func closeButtonDidTouch() {
        dismiss(animated: true)
    }
}
