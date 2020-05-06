//
//  GameInfoView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomView: UIView {
    private let gameRoomLabel = GameRoomLabel()
    private let versusLabel: VersusLabel = {
       let label = VersusLabel()
        label.text = VersusViewModel.versusText
        return label
    }()
    private let awayTeamLabel: TitleLabel = {
       let label = TitleLabel()
        label.text = "Heros"
        return label
    }()
    private let homeTeamLabel: TitleLabel = {
       let label = TitleLabel()
        label.text = "Pintos"
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureHomeTeamLabel()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureHomeTeamLabel()
    }
    
    private func configureView() {
        backgroundColor = .white
        layer.cornerRadius = 12
    }
    
    private func configureGameRoomLabel() {
        addSubview(gameRoomLabel)
        
        gameRoomLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        gameRoomLabel.topAnchor.constraint(equalTo: topAnchor, constant: 12).isActive = true
    }
    
    private func configureVersusLabel() {
        addSubview(versusLabel)
        
        versusLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        versusLabel.centerYAnchor.constraint(equalTo: centerYAnchor,
                                            constant: 15).isActive = true
    }
    
    private func configureAwayTeamLabel() {
        addSubview(awayTeamLabel)
        
        awayTeamLabel.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -30).isActive = true
        awayTeamLabel.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureHomeTeamLabel() {
        addSubview(homeTeamLabel)
        
        homeTeamLabel.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 30).isActive = true
        homeTeamLabel.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    func configure(gameRoom: GameRoom) {
        gameRoomLabel.text = "\(GameRoomViewModels.gameRoomDefaultText) \(gameRoom.id)"
        awayTeamLabel.text = gameRoom.awayTeam
        homeTeamLabel.text = gameRoom.homeTeam
    }
}
