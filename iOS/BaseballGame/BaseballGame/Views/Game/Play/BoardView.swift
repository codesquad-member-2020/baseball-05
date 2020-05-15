//
//  BoardView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class BoardView: UIView, WithXib {
    @IBOutlet weak var sbosView: SBOsView!
    @IBOutlet weak var inningLabel: BoardLabel!
    @IBOutlet weak var halfLabel: BoardLabel!
    @IBOutlet weak var offenseOrDefense: BoardLabel!
    
    private let groundView = GroundView()
    let pitchButton = PitchButton()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
        configurGroundView()
        configurePitchButton()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
        configurGroundView()
        configurePitchButton()
    }
    
    private func configurGroundView() {
        guard let view = subviews.first else { return }
        view.addSubview(groundView)
        
        groundView.widthAnchor.constraint(equalTo: self.widthAnchor, multiplier: 0.5).isActive = true
        groundView.heightAnchor.constraint(equalTo: self.widthAnchor, multiplier: 0.5).isActive = true
        groundView.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        groundView.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
    }
    
    private func configurePitchButton() {
        addSubview(pitchButton)
        
        pitchButton.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        pitchButton.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        pitchButton.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.3).isActive = true
        pitchButton.heightAnchor.constraint(equalTo: pitchButton.widthAnchor, multiplier: 26/105).isActive = true
    }
    
    func configureSBOsView(sbo: SBO) {
        sbosView.strikeView.updateCountViews(count: sbo.strikeCount)
        sbosView.ballView.updateCountViews(count: sbo.ballCount)
        sbosView.outView.updateCountViews(count: sbo.outCount)
    }
}
