//
//  LoginButton.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/07.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class LoginButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureLayer()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureLayer()
    }
    
    func configureLayer() {
        layer.borderWidth = 0.6
        layer.borderColor = UIColor.GreyBeigeColor.cgColor
    }
}
