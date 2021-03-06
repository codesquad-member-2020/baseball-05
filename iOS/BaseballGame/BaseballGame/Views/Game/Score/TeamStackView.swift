//
//  TeamStack.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

@IBDesignable
final class TeamStackView: UIStackView {
    override init(frame: CGRect) {
        super.init(frame: frame)
        addInningViewLabels()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        addInningViewLabels()
    }
    
    private func addInningViewLabels() {
        let maxCount = 12
        for _ in 0 ..< maxCount {
            let label = InningViewLabel()
            addArrangedSubview(label)
        }
    }
}
