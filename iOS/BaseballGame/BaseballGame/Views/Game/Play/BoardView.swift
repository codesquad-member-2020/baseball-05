//
//  BoardView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class BoardView: UIView {
    @IBOutlet weak var sbosView: SBOsView!
    @IBOutlet weak var inningLabel: BoardLabel!
    @IBOutlet weak var halfLabel: BoardLabel!
    @IBOutlet weak var offenseOrDefense: BoardLabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
}
