//
//  PitchButton.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PitchButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false 
        configure()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }
    
    private func configure() {
        layer.cornerRadius = 5
        backgroundColor = .black
        titleLabel?.font = UIFont.boldSystemFont(ofSize: 18)
        titleLabel?.textColor = .white
        setTitle("PITCH", for: .normal)
        setTitleColor(titleLabel?.textColor.withAlphaComponent(0.5), for: .highlighted)
        isHidden = true
    }
}
