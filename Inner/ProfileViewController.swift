//
//  ProfileViewController.swift
//  Inner
//
//  Created by Alanna Walton on 12/7/17.
//  Copyright © 2017 Alanna Walton. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth
import FirebaseAuthUI

class ProfileViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    let imagePicker = UIImagePickerController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
        
        let tapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(imageTapped(tapGestureRecognizer:)))
        profileImg.isUserInteractionEnabled = true
        profileImg.addGestureRecognizer(tapGestureRecognizer)
        
        if isViewLoaded {
            NSLog("view loaded. should get display name and picture")
            getProfImage()
        }
        
    }
    
    
    
    @objc func imageTapped(tapGestureRecognizer: UITapGestureRecognizer)
    {
        let tappedImage = tapGestureRecognizer.view as! UIImageView
        
        // Your action
        let alert = UIAlertController(title: "Choose Image", message: nil, preferredStyle: .actionSheet)
        alert.addAction(UIAlertAction(title: "Camera", style: .default, handler: { _ in
            self.openCamera()
        }))
        
        alert.addAction(UIAlertAction(title: "Gallery", style: .default, handler: { _ in
            self.openGallery()
        }))
        
        alert.addAction(UIAlertAction.init(title: "Cancel", style: .cancel, handler: nil))
        
        imagePicker.delegate = self 
        self.present(alert, animated: true, completion: nil)
    }
    
    //choose camera from photo gallery
    func openCamera()
    {
        if(UIImagePickerController .isSourceTypeAvailable(UIImagePickerControllerSourceType.camera))
        {
            imagePicker.sourceType = UIImagePickerControllerSourceType.camera
            imagePicker.allowsEditing = true
            self.present(imagePicker, animated: true, completion: nil)
        }
        else
        {
            let alert  = UIAlertController(title: "Warning", message: "You don't have camera", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    // Choose image from photo gallery
    func openGallery()
    {
        imagePicker.sourceType = UIImagePickerControllerSourceType.photoLibrary
        imagePicker.allowsEditing = true
        self.present(imagePicker, animated: true, completion: nil)
        
    }
    
    //Displays image after it has been chosen
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        
        picker.dismiss(animated: true, completion: nil)
        
        //You will get cropped image here..
        if let image = info[UIImagePickerControllerEditedImage] as? UIImage
        {
            // Get User
            let user = FIRAuth.auth()?.currentUser
            // Get Profile Image
            let imageData = UIImageJPEGRepresentation(image, 0.8)
            
            // imgFile the Photo File.
            func uploadMedia() {
                let storageRef = FIRStorage.storage().reference(withPath: (user?.uid)! + "/profileImage/myImage.jpg")
                let metaData = FIRStorageMetadata()
                metaData.contentType = "image/jpg"
                let uploadTask = storageRef.put(imageData!, metadata: metaData) { (metadata, error) in
                    guard let metadata = metadata else {
                        // Uh-oh, an error occurred!
                        NSLog("failre")
                        NSLog(error as! String)
                        return
                    }
                    // Metadata contains file metadata such as size, content-type, and download URL.
                    let downloadURL = metadata.downloadURL
                    NSLog("success")
                }
                
            }
            uploadMedia()
           
            let userPhoto = UIImage(data: imageData!)
            self.profileImg.image = userPhoto
            
            
        }
        
    }

    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBOutlet weak var profileImg: UIImageView!
    @IBOutlet weak var displayName: UILabel!
    @IBOutlet weak var LocationField: UILabel!
    
   
    
    func getProfImage() {
        // Get User
        let user = FIRAuth.auth()?.currentUser
        
        if (user?.uid)!.isEmpty {
            NSLog("no user ID present")
        } else {
            NSLog((user?.uid)!)
        }
        
        // Storage Ref
        let storageRef = FIRStorage.storage().reference(withPath: (user?.uid)! + "/profileImage/myImage.jpg")
        
        NSLog(storageRef.fullPath)
        
        storageRef.data(withMaxSize: 10*1024*1024, completion: { (data, error) in
            if data != nil {
                NSLog("data found")
                let userPhoto = UIImage(data: data!)
                self.profileImg.image = userPhoto
            }
        })
        displayName.text = user?.displayName
        var ref: FIRDatabaseReference!
        ref = FIRDatabase.database().reference()
        ref.child("users").child((user?.uid)!).observeSingleEvent(of: .value, with: { (snapshot) in
            // Get user value
            let value = snapshot.value as? NSDictionary
            let firebaseLoc = value?["location"] as? String ?? ""
            self.LocationField.text = "Location: " + firebaseLoc
            // ...
        }) { (error) in
            print(error.localizedDescription)
        }

    }
   
    @IBAction func SignOut(_ sender: Any) {
        let firebaseAuth = FIRAuth.auth()
        do {
            try firebaseAuth?.signOut()
            self.performSegue(withIdentifier: "signedOut", sender: self)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
        }
    }
    
    
}
