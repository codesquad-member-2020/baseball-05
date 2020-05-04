//
//  ViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRecordViewController: UIViewController {
    //MARK:- IBOutlet properties
    @IBOutlet weak var gameListLabel: TitleLabel!
    
    //MARK:- Internal properties
    private let gameInfoStackView = GameInfoStackView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureGameInfoStackView()
    }
    
    private func configureGameInfoStackView() {
        view.addSubview(gameInfoStackView)
        gameInfoStackView.topAnchor.constraint(equalTo: gameListLabel.bottomAnchor,
                                               constant: 26).isActive  = true
        let safeArea = view.safeAreaLayoutGuide
        gameInfoStackView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor,
                                                   constant: 10).isActive = true
        gameInfoStackView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor,
                                                    constant: -10).isActive = true
        gameInfoStackView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor,
                                                  constant: -10).isActive = true
    }
}

