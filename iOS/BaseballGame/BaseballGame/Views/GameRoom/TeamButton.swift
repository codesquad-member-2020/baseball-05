//
//  TeamButton.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/13.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class TeamButton: UIButton {
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
        setTitleColor(.black, for: .normal)
        setTitleColor(titleLabel?.textColor.withAlphaComponent(0.2), for: .highlighted)
        titleLabel?.font = UIFont.boldSystemFont(ofSize: 28)
    }
}
