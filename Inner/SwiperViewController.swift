//
//  SwiperViewController.swift
//  Inner
//
//  Created by Alanna Walton on 2/22/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//


import Foundation
import UIKit
import MDCSwipeToChoose

class SwiperViewController: UIViewController, MDCSwipeToChooseDelegate {
    @IBOutlet var swipeView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let options = MDCSwipeToChooseViewOptions()
        options.delegate = self
        options.likedText = "Yum"
        options.likedColor = UIColor.blue
        options.nopeText = "Eww"
        options.nopeColor = UIColor.red
        options.onPan = { state -> Void in
            if state?.thresholdRatio == 1 && state?.direction == .left {
                print("Photo deleted!")
            }
        }
        let view = MDCSwipeToChooseView(frame: CGRect(x:0, y: 1, width:self.view.frame.width, height:self.view.frame.height - 70) , options: options)
        view?.imageView.image = UIImage(named: "Inner Logo.png")
        self.view.addSubview(view!)
    }
    
    
    
}

extension ViewController: MDCSwipeToChooseDelegate {
    
    // This is called when a user didn't fully swipe left or right.
    func viewDidCancelSwipe(_ view: UIView) -> Void{
        print("Couldn't decide, huh?")
    }
    
    // Sent before a choice is made. Cancel the choice by returning `false`. Otherwise return `true`.
    func view(_ view: UIView, shouldBeChosenWith: MDCSwipeDirection) -> Bool {
        if shouldBeChosenWith == .left {
            return true
        } else {
            // Snap the view back and cancel the choice.
            UIView.animate(withDuration: 0.16, animations: { () -> Void in
                view.transform = CGAffineTransform.identity
                view.center = view.superview!.center
            })
            return false
        }
    }
    
    // This is called when a user swipes the view fully left or right.
    func view(_ view: UIView, wasChosenWith: MDCSwipeDirection) -> Void {
        if wasChosenWith == .left {
            print("Photo deleted!")
        } else {
            print("Photo saved!")
        }
    }
}


