//
//  PlayerInfoCell.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/12.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class PlayerInfoCell: UITableViewCell, IdentifiableView {
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var mountsLabel: UILabel!
    @IBOutlet weak var hitsLabel: UILabel!
    @IBOutlet weak var outsLabel: UILabel!
    @IBOutlet weak var avarageLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func configure(playerInfo: PlayerInfo) {
        nameLabel.text = playerInfo.player
        mountsLabel.text = String(playerInfo.mounts)
        hitsLabel.text = String(playerInfo.hits)
        outsLabel.text = String(playerInfo.outs)
        avarageLabel.text = String(playerInfo.average)
    }
}
