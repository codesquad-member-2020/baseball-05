//
//  InningTitleStack.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

@IBDesignable
final class InningTitleStack: UIStackView {
    override init(frame: CGRect) {
        super.init(frame: frame)
        configure()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }
    
    private func configure() {
        addInningLabels()
        addTotalLabel()
    }
    
    private func addInningLabels() {
        let firstInningNumber = 1
        let lastInningNumber = 11
        for number in firstInningNumber ... lastInningNumber {
            let label = InningViewLabel()
            label.text = String(number)
            addArrangedSubview(label)
        }
    }
    
    private func addTotalLabel() {
        let label = InningViewLabel()
        label.text = "R"
        addArrangedSubview(label)
    }
}
