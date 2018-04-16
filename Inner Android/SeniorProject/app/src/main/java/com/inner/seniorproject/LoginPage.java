package com.inner.seniorproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final TextView resetPass = (TextView) findViewById(R.id.ResetPasswrd);
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load the reset password page.
                resetPass.setText("Test");
            }
        });
    }

    public void profile(View view)
    {
        Intent intent = new Intent(this, Profile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //finish();
    }

}
