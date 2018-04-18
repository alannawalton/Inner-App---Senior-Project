package com.inner.seniorproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    Button signBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view)
    {
        EditText email = (EditText) findViewById(R.id.Usernamee);
        EditText pass = (EditText) findViewById(R.id.Password1);
        EditText ver = (EditText) findViewById(R.id.Verify);
        if(pass.getText().toString().equals( ver.getText().toString()) && !email.getText().toString().equals(""))
        {

            //Add uesr to database.
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //Sign In/Up successful.
                                //Load the picture upload page
                                //FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(SignUp.this, InfoPage.class);
                                startActivity(intent);
                            }
                            else
                            {
                                //Not sucessful.
                                Toast.makeText(SignUp.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), " Your passwords don't match",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 40, 40);
            toast.show();
        }
    }
}
