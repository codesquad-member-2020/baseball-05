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

final class TitleView: UIView, IdentifiableView {
    var delegate: TitleViewDelegate?
    @IBOutlet weak var closeButton: UIButton!
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        translatesAutoresizingMaskIntoConstraints = false
    }
    
    @IBAction func closeButtonDidTouch(_ sender: UIButton) {
        delegate?.closeButtonDidTouch()
    }
    
    override var intrinsicContentSize: CGSize {
        return closeButton.intrinsicContentSize
    }
}
