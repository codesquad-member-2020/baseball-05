//
//  LoginViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/07.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class LoginViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func githubLoginButtonTouched(_ sender: OauthLoginButton) {
        dismiss(animated: true)
    }
}
