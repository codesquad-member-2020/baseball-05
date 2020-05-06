//
//  GameInfoLabel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomLabel: UILabel {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureText()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureText()
    }
    
    private func configureText() {
        font = UIFont.systemFont(ofSize: 11, weight: .light)
        textColor = .red
    }
}
