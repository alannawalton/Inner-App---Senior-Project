package com.inner.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_res);

        Intent intent = getIntent();
        HashMap<String, Double> restaurants =(HashMap<String, Double>) intent.getSerializableExtra("map");
        HashMap<String, String> data = (HashMap<String, String>) intent.getSerializableExtra("data");
       // TextView text = (TextView) findViewById(R.id.Outputtxt);
        bm = (TextView) findViewById(R.id.bmTxtRes);
        bmlo = (TextView) findViewById(R.id.bmLotxt);
        sbm = (TextView) findViewById(R.id.sbmrestxt);
        sbmlo= (TextView) findViewById(R.id.sbmlotxt);
        tbm = (TextView) findViewById(R.id.tbmretxt);
        tbmlo = (TextView) findViewById(R.id.tbmlotxt);
        String string = getlarge(restaurants);
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
        tbmlo.setText(data.get(string));

        //text.setText("The score of Bus boys is: "  + restaurants.get("Busboys and Poets") + "\nLocation: "+data.get("Busboys and Poets"));
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
