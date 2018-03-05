//
//  EatingHabitsButton.swift
//  Inner
//
//  Created by Alanna Walton on 1/16/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//

import UIKit

class EatingHabitsButton: UIButton {
    
    /*
     // Only override draw() if you perform custom drawing.
     // An empty implementation adversely affects performance during animation.
     override func draw(_ rect: CGRect) {
     // Drawing code
     }
     */
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.borderWidth = 5
        self.layer.borderColor = UIColor.white.cgColor
        self.frame.size.height = 55
    }
    
}

