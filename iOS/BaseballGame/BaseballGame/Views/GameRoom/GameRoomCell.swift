//
//  GameInfoView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomCell: UICollectionViewCell, ReusableView {
    private let gameRoomLabel = GameRoomLabel()
    private let versusLabel = VersusLabel()
    private let awayTeamLabel = TitleLabel()
    private let homeTeamLabel = TitleLabel()
    
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
        contentView.backgroundColor = .white
        contentView.layer.cornerRadius = 12
    }
    
    private func configureGameRoomLabel() {
        contentView.addSubview(gameRoomLabel)
        
        gameRoomLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        gameRoomLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 12).isActive = true
    }
    
    private func configureVersusLabel() {
        contentView.addSubview(versusLabel)
        
        versusLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        versusLabel.centerYAnchor.constraint(equalTo: contentView.centerYAnchor,
                                            constant: 15).isActive = true
    }
    
    private func configureAwayTeamLabel() {
        awayTeamLabel.textColor = .black
        configureAwayTeamLabelConstraints()
    }
    
    private func configureAwayTeamLabelConstraints() {
        contentView.addSubview(awayTeamLabel)
        
        awayTeamLabel.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -30).isActive = true
        awayTeamLabel.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureHomeTeamLabel() {
        homeTeamLabel.textColor = .black
        configureHomeTeamLabelConstraints()
    }
    
    private func configureHomeTeamLabelConstraints() {
        contentView.addSubview(homeTeamLabel)
        
        homeTeamLabel.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 30).isActive = true
        homeTeamLabel.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    func configure(gameRoom: GameRoom) {
        gameRoomLabel.text = "\(GameRoomViewModels.gameRoomDefaultText) \(gameRoom.id)"
        awayTeamLabel.text = gameRoom.awayTeam
        homeTeamLabel.text = gameRoom.homeTeam
    }
}
