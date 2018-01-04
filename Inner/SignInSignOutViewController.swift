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
import FirebaseDatabaseUI
import FirebaseGoogleAuthUI
import FirebaseFacebookAuthUI
import AVFoundation
import AVKit
import RSKImageCropper

class SignInSignOutViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate, RSKImageCropViewControllerDelegate {
    
    
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
        }

    }
    
    @IBAction func saveProfileData(_ sender: Any) {
    }
}
