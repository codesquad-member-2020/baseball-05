//
//  GameInfoStackView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/04.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class GameRoomCollectionView: UICollectionView {
    override init(frame: CGRect = .zero, collectionViewLayout layout: UICollectionViewLayout) {
        super.init(frame: frame, collectionViewLayout: layout)
        translatesAutoresizingMaskIntoConstraints = false
        backgroundColor = .none
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        backgroundColor = .none
    }
}

final class GameRoomCollectionViewFlowLayout: UICollectionViewFlowLayout {
    init(superFrame: CGRect) {
        super.init()
        configureItemSize(superFrame: superFrame)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    private func configureItemSize(superFrame: CGRect) {
        itemSize = CGSize(width: superFrame.width * 0.9 , height: superFrame.height * 0.15)
    }
}
