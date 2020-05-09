//
//  IdentifiableViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/09.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

protocol IdentifiableViewController where Self: UIViewController {
    static var identifier: String { get }
}

extension IdentifiableViewController {
    static var identifier: String {
           return String(describing: self)
    }
}
