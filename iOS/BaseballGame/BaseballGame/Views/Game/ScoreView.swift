//
//  ScoreView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class ScoreView: UIView, WithXib {
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
    }
}
