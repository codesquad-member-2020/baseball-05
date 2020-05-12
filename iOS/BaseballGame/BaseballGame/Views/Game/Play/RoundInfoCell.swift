//
//  BatterInfoCell.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class RoundInfoCell: UITableViewCell, IdentifiableView {
    @IBOutlet weak var orderLabel: OrderLabel!
    @IBOutlet weak var sboLabel: UILabel!
    @IBOutlet weak var countLabel: UILabel!  
    
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
    
    func configure(orderText: String, roundInfo: RoundInfo) {
        orderLabel.text = orderText
        sboLabel.text = roundInfo.decision
        countLabel.text = "\(roundInfo.strike)-\(roundInfo.ball)"
    }
}
