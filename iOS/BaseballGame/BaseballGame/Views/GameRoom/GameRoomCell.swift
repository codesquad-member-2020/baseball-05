//
//  GameInfoView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomCell: UICollectionViewCell, IdentifiableView {
    private let gameRoomLabel = GameRoomLabel()
    private let versusLabel = VersusLabel()
    private let awayTeamButton = TeamButton()
    private let homeTeamButton = TeamButton()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureHomeTeamLabel()
        configureDelegates()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureView()
        configureGameRoomLabel()
        configureVersusLabel()
        configureAwayTeamLabel()
        configureHomeTeamLabel()
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
        contentView.addSubview(gameRoomLabel)
        
        gameRoomLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        gameRoomLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 12).isActive = true
    }
    
    private func configureVersusLabel() {
        contentView.addSubview(versusLabel)
        
        versusLabel.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        versusLabel.centerYAnchor.constraint(equalTo: contentView.centerYAnchor,
                                            constant: 10).isActive = true
    }
    
    private func configureAwayTeamLabel() {
        contentView.addSubview(awayTeamButton)
        
        awayTeamButton.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -30).isActive = true
        awayTeamButton.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
    }
    
    private func configureHomeTeamLabel() {
        contentView.addSubview(homeTeamButton)
        
        homeTeamButton.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 30).isActive = true
        homeTeamButton.lastBaselineAnchor.constraint(equalTo: versusLabel.lastBaselineAnchor).isActive = true
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
        gameRoomLabel.text = "\(GameRoomViewModels.gameRoomDefaultText) \(gameRoom.id)"
        awayTeamButton.setTitle(gameRoom.awayTeam.teamName, for: .normal)
        homeTeamButton.setTitle(gameRoom.homeTeam.teamName, for: .normal)
    }
}
