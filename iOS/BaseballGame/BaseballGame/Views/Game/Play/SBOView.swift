//
//  CountView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/10.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

@IBDesignable
final class SBOView: UIView, WithXib {
    @IBOutlet weak var sboLabel: BoardLabel!
    @IBOutlet weak var countStack: UIStackView!
    
    private var countViews: [CountView]!
    var countColor: UIColor!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        insertXibView()
        insertCountViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        insertXibView()
        insertCountViews()
    }
    
    private func insertCountViews() {
        countViews = initCountViews()
        countViews.forEach {
            countStack.addArrangedSubview($0)
            $0.layer.cornerRadius = countStack.bounds.height / 2
        }
    }
    
    private func initCountViews() -> [CountView] {
        var views = [CountView]()
        let totalCount = 4
        for _ in 0 ..< totalCount {
            let view = CountView()
            views.append(view)
        }
        return views
    }
    
    func updateCountViews(count: Int) {
        hideAllCountView()
        for index in 0 ..< count {
            countViews[index].show(color: countColor)
        }
    }
    
    func hideAllCountView() {
        countViews.forEach { $0.hide() }
    }
}
