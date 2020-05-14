//
//  OrderView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/14.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class OrderView: UIView {
    let orderLabel = OrderLabel()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configure()
        configureOrderLabel()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
        configureOrderLabel()
    }
    
    private func configure() {
        backgroundColor = .black
        widthAnchor.constraint(equalTo: heightAnchor).isActive = true
        layer.cornerRadius = bounds.width / 2
    }
    
    private func configureOrderLabel() {
        addSubview(orderLabel)
        orderLabel.frame = bounds
    }
}
