//
//  CountView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

@IBDesignable
final class SBOView: UIView, WithXib {
    @IBOutlet weak var sboLabel: BoardLabel!
    @IBOutlet weak var countStack: UIStackView!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
    }
}
