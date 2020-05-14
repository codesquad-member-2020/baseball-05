//
//  BoardView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class BoardView: UIView, WithXib {
    @IBOutlet weak var sbosView: SBOsView!
    @IBOutlet weak var inningLabel: BoardLabel!
    @IBOutlet weak var halfLabel: BoardLabel!
    @IBOutlet weak var offenseOrDefense: BoardLabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
    }
    
    func configureSBOsView(sbo: SBO) {
        sbosView.strikeView.updateCountViews(count: sbo.strikeCount)
        sbosView.ballView.updateCountViews(count: sbo.ballCount)
        sbosView.outView.updateCountViews(count: sbo.outCount)
    }
    
    func configureInningInfoLabels(inning: Inning) {
        inningLabel.text = "\(String(inning.id))회"
        halfLabel.text = inning.half.description
        offenseOrDefense.text = "must fix"
    }
}
