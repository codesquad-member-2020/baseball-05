//
//  RhombusView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/15.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class RhombusView: UIView {
    private let color: UIColor
    
    init(frame: CGRect = .zero, color: UIColor) {
        self.color = color
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
    }
    
    required init?(coder: NSCoder) {
        self.color = .clear
        super.init(coder: coder)
    }
    
    override func draw(_ rect: CGRect) {
        let rhombusPath = UIBezierPath()
        let widthPoint = rect.width / 2
        let heightPoint = rect.height / 2
        let startPoint = CGPoint(x: widthPoint, y: 0)
        rhombusPath.move(to: startPoint)
        rhombusPath.addLine(to: CGPoint(x: rect.width, y: heightPoint))
        rhombusPath.addLine(to: CGPoint(x: widthPoint, y: rect.height))
        rhombusPath.addLine(to: CGPoint(x: 0, y: heightPoint))
        self.color.setFill()
        rhombusPath.fill()
    }
}
