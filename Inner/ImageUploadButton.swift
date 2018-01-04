//
//  ImageUploadButton.swift
//  Inner
//
//  Created by Alanna Walton on 12/7/17.
//  Copyright © 2017 Alanna Walton. All rights reserved.
//

import UIKit

class ImageUploadButton: UIButton {

    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.borderColor = UIColor.white.cgColor
    }

}
