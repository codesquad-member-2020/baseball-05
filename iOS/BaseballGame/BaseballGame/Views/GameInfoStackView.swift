//
//  GameInfoStackView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameInfoStackView<T: GameInfoView>: UIView {
    private let verticalStackView: UIStackView = {
       let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .vertical
        stackView.distribution = .fillEqually
        stackView.spacing = 5
        return stackView
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configureVerticalStackView()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureVerticalStackView()
    }
    
    private func configureVerticalStackView() {
        addSubview(verticalStackView)
        
        verticalStackView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        verticalStackView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        verticalStackView.widthAnchor.constraint(equalTo: widthAnchor).isActive = true
        verticalStackView.heightAnchor.constraint(equalTo: heightAnchor).isActive = true
    }
    
    func addArrangedSubView(_ gameInfoView: T) {
        verticalStackView.addArrangedSubview(verticalStackView)
    }
}
