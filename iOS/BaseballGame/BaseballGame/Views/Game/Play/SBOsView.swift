//
//  BoardCountsView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class SBOsView: UIView, WithXib {
    @IBOutlet weak var strikeView: SBOView!
    @IBOutlet weak var ballView: SBOView!
    @IBOutlet weak var outView: SBOView!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
        configureSBOLabels()
        configureSBOCountColor()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
        configureSBOLabels()
        configureSBOCountColor()
    }
    
    private func configureSBOLabels() {
        strikeView.sboLabel.text = "S"
        ballView.sboLabel.text = "B"
        outView.sboLabel.text = "O"
    }
    
    private func configureSBOCountColor() {
        strikeView.countColor = .systemYellow
        ballView.countColor = .systemGreen
        outView.countColor = .systemRed
    }
}
