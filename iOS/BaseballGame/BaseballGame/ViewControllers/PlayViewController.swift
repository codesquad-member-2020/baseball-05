//
//  PlayViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTitleView()
    }
    
    private func configureTitleView() {
        let view = Bundle.main.loadNibNamed(TitleView.identifier, owner: self, options: nil)?.first as! TitleView
    }
}
