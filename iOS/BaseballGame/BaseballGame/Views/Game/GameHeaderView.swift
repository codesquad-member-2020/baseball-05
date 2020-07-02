//
//  TitleView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit


protocol GameHeaderViewDelegate: class {
    func closeButtonDidTouch()
}

final class GameHeaderView: UIView, WithXib {
    weak var delegate: GameHeaderViewDelegate?
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
    }
    
    @IBAction func closeButtonDidTouch(_ sender: UIButton) {
        delegate?.closeButtonDidTouch()
    }
}
