//
//  TitleView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit


protocol TitleViewDelegate {
    func closeButtonDidTouch()
}

final class TitleView: UIView, IdentifiableView {
    var delegate: TitleViewDelegate?
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }
    
    private func configure() {
        guard let view = loadViewFromNib() else { return }
        view.frame = bounds
        self.addSubview(view)
    }
    
    private func loadViewFromNib() -> UIView? {
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: TitleView.identifier, bundle: bundle)
        return nib.instantiate(withOwner: self, options: nil).first as? UIView
    }
    
    @IBAction func closeButtonDidTouch(_ sender: UIButton) {
        delegate?.closeButtonDidTouch()
    }
}
