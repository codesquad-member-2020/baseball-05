//
//  GameInfoView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomCell: UICollectionViewCell, IdentifiableView {
    private let gameRoomTitleLabel = GameRoomLabel()
    private let versusLabel = VersusLabel()
    private let awayTeamLabel = TitleLabel()
    private let awayTeamUserLabel = UserLabel()
    private let homeTeamLabel = TitleLabel()
    private let homeTeamUserLabel = UserLabel()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureAwayTeamUserLabel()
        configureHomeTeamLabel()
        configureHomeTeamUserLabel()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureAwayTeamUserLabel()
        configureHomeTeamLabel()
        configureHomeTeamUserLabel()
    }
    
    private func configureView() {
        contentView.backgroundColor = .white
        contentView.layer.cornerRadius = 12
    }
    
    private func configureGameRoomLabel() {
        contentView.addSubview(gameRoomTitleLabel)
        
        gameRoomTitleLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        gameRoomTitleLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 12).isActive = true
    }

    private func configureVersusLabel() {
        contentView.addSubview(versusLabel)
        
        versusLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        versusLabel.centerYAnchor.constraint(equalTo: contentView.centerYAnchor,
                                            constant: 10).isActive = true
    }
    
    private func configureAwayTeamLabel() {
        contentView.addSubview(awayTeamLabel)
        
        awayTeamLabel.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -30).isActive = true
        awayTeamLabel.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureAwayTeamUserLabel() {
        contentView.addSubview(awayTeamUserLabel)
        
        awayTeamUserLabel.topAnchor.constraint(equalTo: awayTeamLabel.lastBaselineAnchor, constant: 12).isActive = true
        awayTeamUserLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor, constant: -90).isActive = true
    }
    
    private func configureHomeTeamLabel() {
        contentView.addSubview(homeTeamLabel)
        
        homeTeamLabel.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 30).isActive = true
        homeTeamLabel.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureHomeTeamUserLabel() {
        contentView.addSubview(homeTeamUserLabel)
        
        homeTeamUserLabel.topAnchor.constraint(equalTo: homeTeamLabel.lastBaselineAnchor, constant: 12).isActive = true
        homeTeamUserLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor, constant: 90).isActive = true
    }
    
    func configure(gameRoom: GameRoom) {
        gameRoomTitleLabel.text = "\(GameRoomViewModels.gameRoomDefaultText) \(gameRoom.id)"
        awayTeamLabel.text = gameRoom.awayTeam.teamName
        awayTeamUserLabel.text = gameRoom.awayTeam.userName
        homeTeamLabel.text = gameRoom.homeTeam.teamName
        homeTeamUserLabel.text = gameRoom.homeTeam.userName
    }
}
