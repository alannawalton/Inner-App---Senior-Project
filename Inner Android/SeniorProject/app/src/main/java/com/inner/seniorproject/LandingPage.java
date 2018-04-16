package com.inner.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.inner.seniorproject.LoginPage;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void login(View view)
    {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        //finish();
    }

    public void  signUp(View view)
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
