//
//  InnerGroupsViewController.swift
//  Inner
//
//  Created by Alanna Walton on 1/11/18.
//  Copyright © 2018 Alanna Walton. All rights reserved.
//

import Foundation
import UIKit
import Firebase
import FirebaseAuth
import FirebaseAuthUI

class InnerGroupsViewController: UIViewController {
   
    @IBOutlet private weak var collectionView: UICollectionView!
    var groupCount:Int = 0
    var collectionData = ["1 🏆", "2 🐸", "3 🍩", "4 😸", "5 🤡", "6 👾", "7 👻", "8 👩‍🎤", "9 🎸", "10 🍖", "11 🐯", "12 🌋"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        updateGroupCount{(check) in
            print(check)
            if check == true {
                print("innit")
                self.collectionView?.reloadData()
            }
        }
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Use only when you want to reload your data every time your view is presented.
        /*
         loadData { (check) in
         print(check)
         }
         */
    }
    
    var ref: FIRDatabaseReference! = FIRDatabase.database().reference()
    let user = FIRAuth.auth()?.currentUser
    
    
    // Get user's group count from Firebase
    func updateGroupCount(completionBlock : @escaping ((_ success : Bool?) -> Void)){
        self.ref.child("users").child((user?.uid)!).child("groups").observeSingleEvent(of: .value, with: { (snapshot) in
            // Retrieve data
            let value = snapshot.value as? NSDictionary
            let gCount = value?["count"] as! String
            let gCountInt = Int(gCount)
            
            // Set local variable
            self.groupCount = gCountInt!
            
            // Reload collection view
            completionBlock(true)

        }) { (error) in
            print(error.localizedDescription)
        }
    }

    
    
    @IBAction func addNewGroup(_ sender: Any) {
        self.ref.child("users").child((user?.uid)!).child("groups").observeSingleEvent(of: .value, with: { snapshot in
            let value = snapshot.value as? NSDictionary
            let stringGCount = value?["count"] as! String
            var gCount = Int(stringGCount)
            gCount = gCount! + 1
            let count = gCount as! Int
            self.ref.child("users").child((self.user?.uid)!).child("groups/count").setValue("\(count)")
            self.updateGroupCount{(check) in
                print(check)
                if check == true {
                    self.collectionView?.reloadData()
                }
            }
        })
    }
}


extension InnerGroupsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        NSLog( String(self.groupCount))
        return self.groupCount
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CollectionViewCell", for: indexPath)
        
        return cell
    }
}

