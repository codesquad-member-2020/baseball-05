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
        let titleView = Bundle.main.loadNibNamed(TitleView.identifier, owner: self, options: nil)?.first as! TitleView
        view.addSubview(titleView)
        
        titleView.widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        titleView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor).isActive = true
        titleView.topAnchor.constraint(equalTo: safeArea.topAnchor).isActive = true
    }
}
