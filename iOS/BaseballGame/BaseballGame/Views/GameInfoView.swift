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
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureGameInfoLabel()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureGameInfoLabel()
    }
    
    private func configureGameInfoLabel() {
        addSubview(gameInfoLabel)
        
        gameInfoLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        gameInfoLabel.topAnchor.constraint(equalTo: topAnchor, constant: 10).isActive = true
    }
}
