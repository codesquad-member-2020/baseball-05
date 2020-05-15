//
//  GroundView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/15.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GroundView: UIView {
    private let rhombusView = RhombusView()
    private let firstBase = RhombusView()
    private let secondBase = RhombusView()
    private let thirdBase = RhombusView()
    private let homeBase = HomeBase()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .clear
        translatesAutoresizingMaskIntoConstraints = false
        configureRhombusView()
        configureFirstBase()
        configureSecondeBase()
        configureThirdBase()
        configureHomeBase()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureRhombusView()
        configureFirstBase()
        configureSecondeBase()
        configureThirdBase()
        configureHomeBase()
    }
    
    override func draw(_ rect: CGRect) {
        let groundPath = UIBezierPath()
        let widthPoint = rect.width / 2
        let heightPoint = rect.height / 2
        let startPoint = CGPoint(x: widthPoint, y: 0)
        groundPath.move(to: startPoint)
        groundPath.addLine(to: CGPoint(x: rect.width, y: heightPoint))
        groundPath.addLine(to: CGPoint(x: widthPoint, y: rect.height))
        groundPath.addLine(to: CGPoint(x: 0, y: heightPoint))
        UIColor.gray.setFill()
        groundPath.fill()
    }
    
    private func configureRhombusView() {
        rhombusView.color = UIColor.beigeColor
        addSubview(rhombusView)
        
        rhombusView.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        rhombusView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        rhombusView.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.9).isActive = true
        rhombusView.heightAnchor.constraint(equalTo: widthAnchor, multiplier: 0.9).isActive = true
    }
    
    private func configureFirstBase() {
        firstBase.color = .white
        addSubview(firstBase)
        
        firstBase.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: 5).isActive = true
        firstBase.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        firstBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.13).isActive = true
        firstBase.heightAnchor.constraint(equalTo: firstBase.widthAnchor).isActive = true
    }
    
    private func configureSecondeBase() {
        secondBase.color = .white
        addSubview(secondBase)
        
        secondBase.topAnchor.constraint(equalTo: self.topAnchor, constant: -5).isActive = true
        secondBase.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        secondBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.13).isActive = true
        secondBase.heightAnchor.constraint(equalTo: secondBase.widthAnchor).isActive = true
    }
    
    private func configureThirdBase() {
        thirdBase.color = .white
        addSubview(thirdBase)
        
        thirdBase.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: -5).isActive = true
        thirdBase.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        thirdBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.13).isActive = true
        thirdBase.heightAnchor.constraint(equalTo: thirdBase.widthAnchor).isActive = true
    }
    
    private func configureHomeBase() {
        addSubview(homeBase)
        
        homeBase.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: 5).isActive = true
        homeBase.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        homeBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.1).isActive = true
        homeBase.heightAnchor.constraint(equalTo: homeBase.widthAnchor, multiplier: 1.5).isActive = true
    }
}
