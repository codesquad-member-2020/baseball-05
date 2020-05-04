//
//  ViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameListViewController: UIViewController {
    //MARK:- Internal properties
    private let gameListTitleLabel: TitleLabel = {
        let label = TitleLabel()
        label.text = GameListViewModel.titleText
        label.textColor = GameListViewModel.titleColor
        return label
    }()
    private let gameInfoStackView = GameInfoStackView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureGameListLabel()
        configureGameInfoStackView()
        for _ in 0 ..< 4 {
            gameInfoStackView.add(gameInfoView: GameInfoView())
        }
    }
    
    private func configureGameListLabel() {
        view.addSubview(gameListTitleLabel)
        
        gameListTitleLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        gameListTitleLabel.topAnchor.constraint(equalTo: safeArea.topAnchor,
                                                constant: 26).isActive = true
    }
    
    private func configureGameInfoStackView() {
        view.addSubview(gameInfoStackView)
        gameInfoStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor,
                                                   constant: 10).isActive = true
        gameInfoStackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10).isActive = true
        gameInfoStackView.topAnchor.constraint(equalTo: gameListTitleLabel.bottomAnchor, constant: 43).isActive = true
    }
}
