//
//  ScoreView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

@IBDesignable
final class ScoreView: UIView, WithXib {
    @IBOutlet weak var awayTeamName: TitleLabel!
    @IBOutlet weak var awayTeamScore: TitleLabel!
    @IBOutlet weak var homeTeamName: TitleLabel!
    @IBOutlet weak var homeTeamScore: TitleLabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
    }
}
