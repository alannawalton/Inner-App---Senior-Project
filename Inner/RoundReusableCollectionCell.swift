//
//  RoundReusableCollectionCell.swift
//  Inner
//
//  Created by Alanna Walton on 1/18/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//

import UIKit

class RoundReusableCollectionCell:UICollectionViewCell {
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.cornerRadius = 48
        self.layer.backgroundColor = UIColor.white.cgColor
        
        
    }
}
