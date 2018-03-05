//
//  EatingHabitsViewController.swift
//  Inner
//
//  Created by Alanna Walton on 1/16/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//

import Foundation
import UIKit
import Firebase
import FirebaseAuth
import FirebaseAuthUI

class EatingHabitsViewController: UIViewController {
    @IBOutlet weak var collectionView: UICollectionView!
  
    var collectionData = ["1 🏆", "2 🐸", "3 🍩", "4 😸", "5 🤡", "6 👾", "7 👻", "8 👩‍🎤", "9 🎸", "10 🍖", "11 🐯", "12 🌋"]

    class Singleton: NSObject {
        static let sharedInstance = Singleton()
        var clickedType:Int = 3
    }
    
    let dietTypes = ["Paleo", "Keto", "Vegan", "Vegetarian", "Pescatarian", "Flexatarian"]
    let allergies = ["Nuts", "Gluten", "Dairy", "Strawberries", "Bread", "Crawfish"]
    let dislikes = ["Cheese", "Noodles", "Rice", "Nuts", "Beef", "Shrimp", "Seafood", "Crawfish", "Spicy"]

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
         NSLog("Entered EatingHabits view controller")
        
        DispatchQueue.main.async{
             NSLog("Clicked type: " + String(Singleton.sharedInstance.clickedType))
            self.collectionView?.reloadData()
        }
       
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }

    @IBAction func clickedDietType(_ sender: Any) {
        NSLog("option 1")
        setClickedType(num: 1)
        self.performSegue(withIdentifier: "HabitsPicker", sender: self)
        refreshCollections()
    }

    @IBAction func clickedAllergies(_ sender: Any) {
        setClickedType(num: 2)
        self.performSegue(withIdentifier: "HabitsPicker", sender: self)
        refreshCollections()
    }

    @IBAction func clickedDislikes(_ sender: Any) {
        setClickedType(num: 3)
        self.performSegue(withIdentifier: "HabitsPicker", sender: self)
        refreshCollections()
    }
    
    func refreshCollections() {
        DispatchQueue.main.async{
            NSLog("Clicked type: " + String( Singleton.sharedInstance.clickedType))
            self.collectionView?.reloadData()
        }

    }
    
    func setClickedType(num: Int) {
         Singleton.sharedInstance.clickedType = num
        
    }
    
    func getClickedType() -> Int {
        return Singleton.sharedInstance.clickedType
    }
}

// MARK: - UICollectionViewDataSource
extension EatingHabitsViewController: UICollectionViewDelegate, UICollectionViewDataSource {

    //1
    func collectionView(_ collectionView: UICollectionView,
                                 numberOfItemsInSection section: Int) -> Int {
        
        NSLog("sshow collections")
        let cType:Int = self.getClickedType()
        NSLog("Later Clicked type: " + String(cType))
        if cType == 1 {
            NSLog("\(dietTypes.count)")
            return dietTypes.count
        }
        if cType == 2 {
            NSLog("\(allergies.count)")
            return allergies.count
        }
        NSLog("\(self.dislikes.count)")
        return dislikes.count
    }

    //2
    func collectionView(_ collectionView: UICollectionView,
                                 cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "EatingGroupsCell",
                                                      for: indexPath)
        // Configure the cell
        if let label = cell.viewWithTag(50) as? UILabel {
           let cType:Int = self.getClickedType()
            if cType == 1 {
                label.text = dietTypes[indexPath.row]
            }
            if cType == 2 {
               label.text = allergies[indexPath.row]
            }
            if cType == 3 {
                label.text = dislikes[indexPath.row]
            }
        }
        return cell
    }
    
    // change background color when user touches cell
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let cell = collectionView.cellForItem(at: indexPath)
        if cell?.backgroundColor == UIColor.red {
            cell?.backgroundColor = UIColor.white
        } else {
            cell?.backgroundColor = UIColor.red
        }
    }
    
}


