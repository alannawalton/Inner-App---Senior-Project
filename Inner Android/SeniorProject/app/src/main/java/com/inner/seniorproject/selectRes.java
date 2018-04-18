package com.inner.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class selectRes extends AppCompatActivity {

    TextView bm, bmlo;
    TextView sbm, sbmlo;
    TextView tbm, tbmlo;
    private DatabaseReference root;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_res);

        database = FirebaseDatabase.getInstance();
        root = database.getReference("users");

        // TextView text = (TextView) findViewById(R.id.Outputtxt);
        bm = (TextView) findViewById(R.id.bmTxtRes);
        bmlo = (TextView) findViewById(R.id.bmLotxt);
        sbm = (TextView) findViewById(R.id.sbmrestxt);
        sbmlo= (TextView) findViewById(R.id.sbmlotxt);
        tbm = (TextView) findViewById(R.id.tbmretxt);
        tbmlo = (TextView) findViewById(R.id.tbmlotxt);

        //text.setText("The score of Bus boys is: "  + restaurants.get("Busboys and Poets") + "\nLocation: "+data.get("Busboys and Poets"));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        final HashMap<String, Double> restaurants =(HashMap<String, Double>) intent.getSerializableExtra("map");
        final HashMap<String, String> data = (HashMap<String, String>) intent.getSerializableExtra("data");
        String grpname = (String) intent.getStringExtra("GroupName");

        Log.v("First msg", "Group name from intent: "+ grpname);

        //DatabaseReference user = root.child("user");
        final DatabaseReference groupName = root.child(grpname);

        // putthing this in on start function or after waiting

        //My Restaurants
        final HashMap<String, Double> rest = new HashMap<String, Double>();
        groupName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user: dataSnapshot.getChildren())
                {
                    for (DataSnapshot restaurant: user.getChildren())
                    {
                        //This is where we should to the score calculation.
                        if(rest.containsKey(restaurant.getKey()))
                        {
                            rest.put(restaurant.getKey(), rest.get(restaurant.getKey()) + restaurant.getValue(Double.class));
                        }
                        else
                        {
                            rest.put(restaurant.getKey(), restaurant.getValue(Double.class));
                        }
                    }
                }

                //Setting the results to the text Fields.
                String string = getlarge(rest);
                bm.setText(string);
                rest.remove(string);
                bmlo.setText(data.get(string));
                string = getlarge(rest);
                sbm.setText(string);
                rest.remove(string);
                sbmlo.setText(data.get(string));
                string = getlarge(rest);
                tbm.setText(string);
                rest.remove(string);
                tbmlo.setText(data.get(string));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*String string = getlarge(restaurants);
        bm.setText(string);
        restaurants.remove(string);
        bmlo.setText(data.get(string));
        string = getlarge(restaurants);
        sbm.setText(string);
        restaurants.remove(string);
        sbmlo.setText(data.get(string));
        string = getlarge(restaurants);
        tbm.setText(string);
        restaurants.remove(string);
        tbmlo.setText(data.get(string));*/

    }

    private String getlarge(HashMap res)
    {
        Double big = -106.0;
        String result = "";
        Set set = res.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext())
        {
            Map.Entry entry = (Map.Entry) i.next();
            Double temp = (Double)entry.getValue();
            if(temp > big)
            {
                big = temp;
                result = (String)entry.getKey();
            }
        }
        return  result;
    }

    public void backtopro(View view)
    {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
