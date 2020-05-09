//
//  ReusableView.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/07.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

protocol IdentifiableView where Self: UIView {
    static var identifier: String { get }
}

extension IdentifiableView {
    static var identifier: String {
           return String(describing: self)
    }
}

protocol WithXib: IdentifiableView {
    func insertXibView()
}

extension WithXib {
    func insertXibView() {
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: Self.identifier, bundle: bundle)
        guard let view =  nib.instantiate(withOwner: self, options: nil).first as? UIView else { return }
        view.frame = bounds
        self.addSubview(view)
    }
}
