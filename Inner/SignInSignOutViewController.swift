//
//  SignInSignOutViewController.swift
//  Inner
//
//  Created by Alanna Walton on 12/7/17.
//  Copyright © 2017 Alanna Walton. All rights reserved.
//


import Foundation
import UIKit
import Firebase
import FirebaseAuth
import FirebaseAuthUI
import FirebaseDatabase
import FirebaseDatabaseUI
import FirebaseGoogleAuthUI
import FirebaseFacebookAuthUI
import AVFoundation
import AVKit

class SignInSignOutViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    
    let imagePicker = UIImagePickerController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        //Looks for single or multiple taps.
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard))
        
        //Uncomment the line below if you want the tap not not interfere and cancel other interactions.
        //tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
        imagePicker.delegate = self
    }
    
    //Calls this function when the tap is recognized.
    @objc func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }
    
    @IBOutlet weak var passwordField: LoginTextField!
    @IBOutlet weak var emailField: LoginTextField!
    
    @IBAction func signUp(_ sender: Any) {
        if email_SignUp.text == "" {
            let alertController = UIAlertController(title: "Error", message: "Please enter your email and password", preferredStyle: .alert)
            
            let defaultAction = UIAlertAction(title: "OK", style: .cancel, handler: nil)
            alertController.addAction(defaultAction)
            
            present(alertController, animated: true, completion: nil)
        } else {
            
            FIRAuth.auth()?.createUser(withEmail: email_SignUp.text!, password: password_SignUp.text!) { (user, error) in
                
                if error == nil {
                    print("You have successfully signed up")
                    //Goes to the Setup page which lets the user take a photo for their profile picture and also chose a username
                    self.finishSignUp()
                    
                    
                } else {
                    let alertController = UIAlertController(title: "Error", message: error?.localizedDescription, preferredStyle: .alert)
                    
                    let defaultAction = UIAlertAction(title: "OK", style: .cancel, handler: nil)
                    alertController.addAction(defaultAction)
                    
                    self.present(alertController, animated: true, completion: nil)
                }
            }
        }
    }
    
    func finishSignUp(){
        self.performSegue(withIdentifier: "CompleteSignUp", sender: self)
    }
    
    @IBOutlet weak var email_SignUp: LoginTextField!
    @IBOutlet weak var password_SignUp: LoginTextField!
    @IBOutlet weak var displayName: LoginTextField!
    @IBOutlet weak var phoneNumber: LoginTextField!
    @IBOutlet weak var imagePickerButton: ImageUploadButton!
    @IBOutlet weak var location: LoginTextField!
    
    
    // Get options for uploading an image
    @IBAction func takeOrGetPicture(_ sender: ImageUploadButton) {
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
            self.imagePickerButton.setBackgroundImage(image, for: .normal)
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
            
        }
        
    }
    
    @IBOutlet weak var signIn_Email: LoginTextField!
    
    @IBOutlet weak var signIn_password: LoginTextField!
    
    @IBAction func completeSignIn(_ sender: Any) {
        
        if signIn_Email.text == "" || signIn_password.text == "" {
            
            //Alert to tell the user that there was an error because they didn't fill anything in the textfields because they didn't fill anything in
            
            let alertController = UIAlertController(title: "Error", message: "Please enter an email and password.", preferredStyle: .alert)
            
            let defaultAction = UIAlertAction(title: "OK", style: .cancel, handler: nil)
            alertController.addAction(defaultAction)
            
            self.present(alertController, animated: true, completion: nil)
            
        } else {
            
            FIRAuth.auth()?.signIn(withEmail: signIn_Email.text!, password: signIn_password.text!) { (user, error) in
                
                if error == nil {
                    
                    //Print into the console if successfully logged in
                    print("You have successfully logged in")
                    
                    //Go to the HomeViewController if the login is sucessful
                    let vc = self.storyboard?.instantiateViewController(withIdentifier: "Home")
                    self.present(vc!, animated: true, completion: nil)
                    
                } else {
                    
                    //Tells the user that there is an error and then gets firebase to tell them the error
                    let alertController = UIAlertController(title: "Error", message: error?.localizedDescription, preferredStyle: .alert)
                    
                    let defaultAction = UIAlertAction(title: "OK", style: .cancel, handler: nil)
                    alertController.addAction(defaultAction)
                    
                    self.present(alertController, animated: true, completion: nil)
                }
            }
        }
    }
    
    
    @IBAction func saveProfData(_ sender: Any) {
        // Get current username
        
        if let user = FIRAuth.auth()?.currentUser {
            let changeRequest = user.profileChangeRequest()
            if displayName != nil{
                changeRequest.displayName = displayName.text
                changeRequest.commitChanges { error in
                    if let error = error {
                        // An error happened.
                        NSLog(error as! String)
                    } else {
                        // Profile updated.
                        NSLog("successory")
                        self.saveOtherUserData(user: user)
                        //Go to the HomeViewController if data save successful
                        let vc = self.storyboard?.instantiateViewController(withIdentifier: "Home")
                        self.present(vc!, animated: true, completion: nil)
                    }
                }
            }
            
        }
    }
    
    func saveOtherUserData(user: FIRUser) {
        var ref: FIRDatabaseReference!
        
        ref = FIRDatabase.database().reference()
        
        ref.child("users").child(user.uid).setValue(["phoneNumber": phoneNumber.text, "location": location.text])
        ref.child("users/\(user.uid)/groups").setValue(["count": 0]) 
        
    }
    
    
}

