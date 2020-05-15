//
//  GroundView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/15.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GroundView: UIView {
    private let rhombusView = RhombusView(color: .black)
    private let firstBase = RhombusView(color: .white)
    private let secondBase = RhombusView(color: .white)
    private let thirdBase = RhombusView(color: .white)
    private let homeBase = HomeBase()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureFirstBase()
        configureSecondeBase()
        configureThirdBase()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureFirstBase()
        configureSecondeBase()
        configureThirdBase()
    }
    
    private func configureFirstBase() {
        addSubview(firstBase)
        
        firstBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.1).isActive = true
        firstBase.heightAnchor.constraint(equalTo: firstBase.widthAnchor).isActive = true
    }
    
    private func configureSecondeBase() {
        addSubview(secondBase)
        
        secondBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.1).isActive = true
        secondBase.heightAnchor.constraint(equalTo: secondBase.widthAnchor).isActive = true
    }
    
    private func configureThirdBase() {
        addSubview(thirdBase)
        
        thirdBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.1).isActive = true
        thirdBase.heightAnchor.constraint(equalTo: thirdBase.heightAnchor).isActive = true
    }
    
    private func configureHomeBase() {
        addSubview(homeBase)
        
        homeBase.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.1).isActive = true
        homeBase.heightAnchor.constraint(equalTo: homeBase.widthAnchor, multiplier: 1.5).isActive = true
    }
    
    private func configureRhombusView() {
        addSubview(rhombusView)
        
        rhombusView.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.9).isActive = true
        rhombusView.heightAnchor.constraint(equalTo: widthAnchor, multiplier: 0.9).isActive = true
        rhombusView.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        rhombusView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
    }
}
