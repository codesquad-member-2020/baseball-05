//
//  OrderView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class OrderLabel: UILabel {
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureText()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureText()
    }
    
    private func configureText() {
        textColor = .white
        textAlignment = .center
    }
}
