package com.inner.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button signBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view)
    {
        EditText pass = (EditText) findViewById(R.id.Password1);
        EditText ver = (EditText) findViewById(R.id.Verify);
        if(pass.getText().toString().equals( ver.getText().toString()))
        {
            //Load the picture upload page
            Intent intent = new Intent(this, InfoPage.class);
            startActivity(intent);
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
