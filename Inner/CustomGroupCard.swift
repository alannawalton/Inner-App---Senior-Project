//
//  CustomGroupCard.swift
//  Inner
//
//  Created by Alanna Walton on 1/12/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//

import Foundation
import UIKit
@IBDesignable

class CustomGroupCard: UICollectionViewCell {
    override func layoutSubviews() {
        
        //Shadows
        self.layer.shadowColor = UIColor(red: 0, green: 255, blue: 255, alpha: 100).cgColor
        self.layer.masksToBounds = false
        self.layer.shadowOpacity = 0.5
        self.layer.shadowOffset = CGSize(width: -1, height: 1)
        self.layer.shadowRadius = 1
        
        self.layer.shadowPath = UIBezierPath(rect: self.bounds).cgPath
        self.layer.shouldRasterize = true
        
        self.layer.rasterizationScale = UIScreen.main.scale
    }
}
