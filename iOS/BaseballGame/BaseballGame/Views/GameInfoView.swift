//
//  GameInfoView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameInfoView: UIView {
    private let gameInfoLabel = GameInfoLabel()
    private let versusLabel = VersusLabel()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureGameInfoLabel()
        configureVersusLabel()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureGameInfoLabel()
        configureVersusLabel()
    }
    
    private func configureGameInfoLabel() {
        addSubview(gameInfoLabel)
        
        gameInfoLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        gameInfoLabel.topAnchor.constraint(equalTo: topAnchor, constant: 10).isActive = true
    }
    
    private func configureVersusLabel() {
        addSubview(versusLabel)
        
        versusLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        versusLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -20).isActive = true
    }
}
