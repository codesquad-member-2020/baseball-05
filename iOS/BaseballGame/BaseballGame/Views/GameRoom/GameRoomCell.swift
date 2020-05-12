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
    private let awayTeamButton = TeamButton()
    private let awayTeamUserLabel = GameRoomLabel()
    private let homeTeamButton = TeamButton()
    private let homeTeamUserLabel = GameRoomLabel()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamButton()
        configureAwayTeamUserLabel()
        configureHomeTeamButton()
        configureHomeTeamUserLabel()
        configureDelegates()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamButton()
        configureAwayTeamUserLabel()
        configureHomeTeamButton()
        configureHomeTeamUserLabel()
        configureDelegates()
    }
    
    deinit {
        awayTeamButton.removeTarget(self, action: #selector(awayButtonDidTouch), for: .touchUpInside)
        homeTeamButton.removeTarget(self, action: #selector(homeButtonDidTouch), for: .touchUpInside)
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
    
    private func configureAwayTeamButton() {
        contentView.addSubview(awayTeamButton)
        
        awayTeamButton.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -30).isActive = true
        awayTeamButton.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureAwayTeamUserLabel() {
        contentView.addSubview(awayTeamUserLabel)
        
        awayTeamUserLabel.topAnchor.constraint(equalTo: awayTeamButton.lastBaselineAnchor, constant: 12).isActive = true
        awayTeamUserLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor, constant: -90).isActive = true
    }
    
    private func configureHomeTeamButton() {
        contentView.addSubview(homeTeamButton)
        
        homeTeamButton.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 30).isActive = true
        homeTeamButton.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureHomeTeamUserLabel() {
        contentView.addSubview(homeTeamUserLabel)
        
        homeTeamUserLabel.topAnchor.constraint(equalTo: homeTeamButton.lastBaselineAnchor, constant: 12).isActive = true
        homeTeamUserLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor, constant: 90).isActive = true
    }
    
    private func configureDelegates() {
        awayTeamButton.addTarget(self, action: #selector(awayButtonDidTouch), for: .touchUpInside)
        homeTeamButton.addTarget(self, action: #selector(homeButtonDidTouch), for: .touchUpInside)
    }
    
    @objc private func awayButtonDidTouch() {
        
    }
    
    @objc private func homeButtonDidTouch() {
        
    }
    
    func configure(gameRoom: GameRoom) {
        gameRoomTitleLabel.text = "\(GameRoomViewModels.gameRoomDefaultText) \(gameRoom.id)"
        awayTeamButton.setTitle(gameRoom.awayTeam.teamName, for: .normal)
        awayTeamUserLabel.text = gameRoom.awayTeam.userName
        homeTeamButton.setTitle(gameRoom.homeTeam.teamName, for: .normal)
        homeTeamUserLabel.text = gameRoom.homeTeam.userName
    }
}
