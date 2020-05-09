//
//  PrevButton.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/08.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

protocol PrevButtonDelegate {
    func prevButtonDidTouch()
}

final class PrevButton: UIButton {
    var delegate: PrevButtonDelegate?
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        confugure()
        configureTarget()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        confugure()
        configureTarget()
    }
    
    private func confugure() {
        setImage(UIImage(systemName: "arrow.left"), for: .normal)
        tintColor = .systemRed
        imageView?.contentMode = .scaleAspectFit
        imageEdgeInsets = UIEdgeInsets(top: 23, left: 23, bottom: 23, right: 23)
    }
    
    private func configureTarget() {
        addTarget(self, action: #selector(prevButtonDidTouch), for: .touchUpInside)
    }
    
    deinit {
        removeTarget(self, action: #selector(prevButtonDidTouch), for: .touchUpInside)
    }
    
    @objc private func prevButtonDidTouch() {
        delegate?.prevButtonDidTouch()
    }
}
