//
//  HomePageButtons.swift
//  Inner
//
//  Created by Alanna Walton on 1/11/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//

import Foundation
import UIKit
@IBDesignable

class HomePageButtons: UIButton {
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.cornerRadius = 4.0
        self.layer.borderColor =  UIColor(red: 0, green: 255, blue: 255, alpha: 100).cgColor
        self.layer.borderWidth = 4
        self.frame.size.height = 60
        self.alpha = 20
    }
}
