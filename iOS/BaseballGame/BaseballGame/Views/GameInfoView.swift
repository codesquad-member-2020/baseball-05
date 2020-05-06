//
//  GameInfoView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameInfoView: UIView {
    private let gameInfoLabel = GameInfoLabel()
    private let versusLabel: VersusLabel = {
       let label = VersusLabel()
        label.text = VersusViewModel.versusText
        return label
    }()
    private let awayTeamLabel = TitleLabel()
    private let homeTeamLabel = TitleLabel()
    
    init(number: Int, frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureGameInfoLabel(number: number)
        configureView()
        configureGameInfoLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureHomeTeamLabel()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureView()
        configureGameInfoLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureHomeTeamLabel()
    }
    
    private func configureGameInfoLabel(number: Int) {
        gameInfoLabel.text = "\(GameListViewModel.gameInfoDefaultText) \(String(number))"
    }
    
    private func configureView() {
        backgroundColor = .white
        layer.cornerRadius = 12
    }
    
    private func configureGameInfoLabel() {
        addSubview(gameInfoLabel)
        
        gameInfoLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        gameInfoLabel.topAnchor.constraint(equalTo: topAnchor, constant: 8).isActive = true
    }
    
    private func configureVersusLabel() {
        addSubview(versusLabel)
        
        versusLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        versusLabel.bottomAnchor.constraint(equalTo: bottomAnchor,
                                            constant: -20).isActive = true
    }
    
    private func configureAwayTeamLabel() {
        addSubview(awayTeamLabel)
        
        awayTeamLabel.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -20).isActive = true
        awayTeamLabel.bottomAnchor.constraint(equalTo: bottomAnchor,
                                              constant: -20).isActive = true
    }
    
    private func configureHomeTeamLabel() {
        addSubview(homeTeamLabel)
        
        homeTeamLabel.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 20).isActive = true
        homeTeamLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -20).isActive = true
    }
}
