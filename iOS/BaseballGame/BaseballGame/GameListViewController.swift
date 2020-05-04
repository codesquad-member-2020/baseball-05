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
    private let gameListLabel = TitleLabel()
    private let gameInfoStackView = GameInfoStackView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureGameListLabel()
        configureGameInfoStackView()
    }
    
    private func configureGameListLabel() {
        gameListLabel.text = "게임 목록"
        gameListLabel.textColor = .white
        view.addSubview(gameListLabel)
        
        gameListLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        gameListLabel.topAnchor.constraint(equalTo: safeArea.topAnchor,
                                           constant: 26).isActive = true
    }
    
    private func configureGameInfoStackView() {
        view.addSubview(gameInfoStackView)
        
        gameInfoStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor,
                                                   constant: 10).isActive = true
        gameInfoStackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10).isActive = true
        gameInfoStackView.topAnchor.constraint(equalTo: gameListLabel.bottomAnchor, constant: 26).isActive = true
        let safeArea = view.safeAreaLayoutGuide
        gameInfoStackView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor,
                                                  constant:  -10).isActive = true
    }
    
    
}

final class GameInfoStackView: UIStackView {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
    }
}

