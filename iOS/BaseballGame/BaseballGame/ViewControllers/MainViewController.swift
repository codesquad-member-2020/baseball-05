//
//  MainViewController.swift
//  BaseballGame
//
//  Created by kimdo2297 on 2020/05/07.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class MainViewController: UIViewController {
    private var hasBeenDisplayed = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        if !hasBeenDisplayed {
            showLoginViewController()
            hasBeenDisplayed = true
        }
    }
    
    private func showLoginViewController() {
        guard let loginViewController = storyboard?.instantiateViewController(withIdentifier: LoginViewController.identifier) as? LoginViewController else { return }
        
        loginViewController.modalPresentationStyle = .fullScreen
        present(loginViewController, animated: false)
    }
    
    @IBAction func gamePlayButtonTouch(_ sender: BorderedButton) {
        showGameRoomViewController()
    }
    
    private func showGameRoomViewController() {
        guard let gameRoomViewController = storyboard?.instantiateViewController(withIdentifier: GameRoomViewController.identifier) as? GameRoomViewController else { return }
        navigationController?.pushViewController(gameRoomViewController, animated: true)
    }
}
