//
//  GameInfoStackView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameInfoStackView: UIStackView {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configure()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }
    
    private func configure() {
        axis = .vertical
        distribution = .fillEqually
        spacing = 17
    }
    
    func add(gameInfoView: GameInfoView) {
        addArrangedSubview(gameInfoView)
        
        gameInfoView.widthAnchor.constraint(equalTo: widthAnchor).isActive = true
        gameInfoView.heightAnchor.constraint(equalTo: gameInfoView.widthAnchor, multiplier: 0.28).isActive = true
    }
}
