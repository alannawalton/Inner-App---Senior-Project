package com.inner.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView editLoc = (TextView) findViewById(R.id.editLocation);
        editLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change the Location Page
                editLoc.setText("Test this");
            }
        });

        final TextView schedule = (TextView) findViewById(R.id.Sched);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to schedule page
                schedule(view);
            }
        });

        final TextView eatinpref = (TextView) findViewById(R.id.eatpref);
        eatinpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to edit eating prefences
                prefences(view);

            }
        });

        TextView sgnout = (TextView) findViewById(R.id.signout);
        sgnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to landing page, sign out
                signout(view);
            }
        });
    }

    public void signout(View view)
    {
        Intent intent = new Intent(this, LandingPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void prefences(View view)
    {
        Intent intent = new Intent(this, preferencesPage.class);
        startActivity(intent);
    }

    private void schedule(View view)
    {
        Intent intent = new Intent(this, createGroup.class);
        startActivity(intent);
    }
}
