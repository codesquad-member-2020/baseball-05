//
//  OauthLoginButton.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/07.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class OauthLoginButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureHighlightedColor()
    }
    
    private func configureHighlightedColor() {
        guard let titleLabel = titleLabel else { return }
        setTitleColor(titleLabel.textColor.withAlphaComponent(0.3), for: .highlighted)
    }
}
