//
//  GameInfoStackView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomStackView: UIStackView {
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
    
    func add(gameRoomView: GameRoomView) {
        addArrangedSubview(gameRoomView)
        
        gameRoomView.widthAnchor.constraint(equalTo: widthAnchor).isActive = true
        gameRoomView.heightAnchor.constraint(equalTo: gameRoomView.widthAnchor, multiplier: 0.28).isActive = true
    }
}
