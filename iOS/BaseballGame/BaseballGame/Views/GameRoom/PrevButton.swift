//
//  PrevButton.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/08.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PrevButton: UIButton {
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
        setImage(UIImage(systemName: "arrow.left"), for: .normal)
        tintColor = .systemRed
        imageView?.contentMode = .scaleAspectFit
        imageEdgeInsets = UIEdgeInsets(top: 23, left: 23, bottom: 23, right: 23)
    }
}
