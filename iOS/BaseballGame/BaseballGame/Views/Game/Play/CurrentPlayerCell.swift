//
//  CurrentPlayerCell.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class CurrentPlayerCell: UITableViewCell, IdentifiableView {
    @IBOutlet weak var playerRole: PlayTableLabel!
    @IBOutlet weak var playerName: PlayTableLabel!
    @IBOutlet weak var valueLabel: PlayTableLabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func configure(currentPlayer: CurrentPlayer, by isOffense: Bool) {
        playerName.text = currentPlayer.name
        if let pitcher = currentPlayer as? CurrentPitcher {
            playerRole.text = "투수"
            valueLabel.text = "#\(pitcher.pitches)"
            accessoryType = isOffense ? .none : .checkmark
        } else if let batter = currentPlayer as? CurrentBatter {
            playerRole.text = "타자"
            valueLabel.text = "\(batter.mounts)타석 \(batter.hits)안타"
            accessoryType = isOffense ? .checkmark : .none
        }
    }
}
