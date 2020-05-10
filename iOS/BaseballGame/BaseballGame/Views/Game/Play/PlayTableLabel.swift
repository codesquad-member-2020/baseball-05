//
//  CurrentPlayerLabel.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayTableLabel: UILabel {
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureText()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureText()
    }
    
    private func configureText() {
        font = UIFont.systemFont(ofSize: 19, weight: .medium)
    }
}
