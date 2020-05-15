//
//  HomeView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/15.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class HomeBase: UIView {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        self.backgroundColor = .clear
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func draw(_ rect: CGRect) {
         let homePath = UIBezierPath()
         let widthPoint = rect.width / 2
         let heightPoint = rect.height / 2
         let startPoint = CGPoint(x: widthPoint, y: 0)
         homePath.move(to: startPoint)
         homePath.addLine(to: CGPoint(x: rect.width, y: heightPoint))
         homePath.addLine(to: CGPoint(x: rect.width, y: rect.height))
         homePath.addLine(to: CGPoint(x: widthPoint, y: rect.height))
         homePath.addLine(to: CGPoint(x: 0, y: rect.height))
         homePath.addLine(to: CGPoint(x: 0, y: heightPoint))
         UIColor.white.setFill()
         homePath.fill()
     }
}
