//
//  LoginTextField.swift
//  Inner
//
//  Created by Alanna Walton on 12/7/17.
//  Copyright © 2017 Alanna Walton. All rights reserved.
//

import UIKit
@IBDesignable
class LoginTextField: UITextField {
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.cornerRadius = 4.0
        self.layer.borderColor =  UIColor(white: 1, alpha: 1).cgColor
        self.layer.borderWidth = 1
        self.frame.size.height = 64
        self.alpha = 0.2
        self.attributedPlaceholder = NSAttributedString(string: placeholder!,
                                                         attributes: [NSAttributedStringKey.foregroundColor: UIColor.white])
        
    }
}
