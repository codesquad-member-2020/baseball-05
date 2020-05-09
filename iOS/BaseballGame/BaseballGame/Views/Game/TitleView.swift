//
//  TitleView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit


protocol TitleViewDelegate {
    func closeButtonDidTouch()
}

final class TitleView: UIView {
    @IBOutlet weak var closeButton: UIButton!
    var delegate: TitleViewDelegate?
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureCloseButton()
    }
    
    deinit {
        closeButton.removeTarget(self, action: #selector(closeButtonDidTouch), for: .touchUpInside)
    }
    
    private func configureCloseButton() {
        closeButton.addTarget(self, action: #selector(closeButtonDidTouch), for: .touchUpInside)
    }
    
    @objc private func closeButtonDidTouch() {
        delegate?.closeButtonDidTouch()
    }
}
