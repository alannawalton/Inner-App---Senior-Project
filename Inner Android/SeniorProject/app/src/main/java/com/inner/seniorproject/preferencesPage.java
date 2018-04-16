package com.inner.seniorproject;

import android.content.DialogInterface;
import android.preference.MultiSelectListPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;

public class preferencesPage extends AppCompatActivity {

    Button dbtn;
    TextView dtext;
    String[] dietTypeList = {"Meatatarian", "Pescatarian", "Vegetarian", "Vegan", "Other"};
    boolean[] dchecked;
    ArrayList<Integer> dietItems = new ArrayList<>();
    Button abtn;
    TextView atext;
    String[] alergies = {"Tree Nuts", "Peanuts", "Milk", "Fruit", "Soy", "Garlic", "Egg", "Alpha-gal", "Wheat", "Corn", "Shell Fish", "Sesame"};
    boolean[] achecked;
    ArrayList<Integer> algItems = new ArrayList<>();
    Button disbtn;
    TextView distxt;
    String[] dislike = {"Fish", "Spinach", "Dasheen", "Pudding", "Couscous"};
    boolean[] dischecked;
    ArrayList<Integer> disItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_page);

        dbtn = (Button) findViewById(R.id.dietBtn);
        dtext = (TextView) findViewById(R.id.dietType);
        dchecked = new boolean[dietTypeList.length];

        abtn = (Button) findViewById(R.id.algbtn);
        atext = (TextView) findViewById(R.id.algertxt);
        achecked = new boolean[alergies.length];

        disbtn = (Button) findViewById(R.id.disbtn);
        distxt = (TextView) findViewById(R.id.distxt);
        dischecked = new boolean[dislike.length];

        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dietBuilder = new AlertDialog.Builder(preferencesPage.this);
                dietBuilder.setTitle("Dietary Types");
                dietBuilder.setMultiChoiceItems(dietTypeList, dchecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean ischecked) {
                        if(ischecked)
                        {
                            if(!dietItems.contains(position))
                            {
                                dietItems.add(position);
                            }
                            else
                            {
                                dietItems.remove(position);
                            }
                        }
                    }
                });

                dietBuilder.setCancelable(false);
                dietBuilder.setPositiveButton(R.string.done_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Hand what happens at the end of the selection.
                        //Referrence items dietItems[
                        //What happens when you press ok.
                    }
                });

                dietBuilder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dietBuilder.setNeutralButton(R.string.clear_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for(int i=0; i<dchecked.length; i++)
                        {
                            dchecked[i] = false;
                            dietItems.clear();
                        }
                    }
                });

                final AlertDialog dietDialog = dietBuilder.create();
                dietDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        dietDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff33b5e5"));
                        dietDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ff33b5e5"));
                        dietDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#ff33b5e5"));
                    }
                });
                dietDialog.show();
            }
        });


        abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alergBuilder = new AlertDialog.Builder(preferencesPage.this);
                alergBuilder.setTitle("Allergies and Restrictions");
                alergBuilder.setMultiChoiceItems(alergies, achecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos, boolean chked) {
                        if(chked)
                        {
                            if(!algItems.contains(pos))
                            {
                                algItems.add(pos);
                            }
                            else
                            {
                                algItems.remove(pos);
                            }
                        }
                    }
                });

                alergBuilder.setCancelable(false);
                alergBuilder.setPositiveButton(R.string.done_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Hand what happens at the end of the selection.
                        //Referrence items dietItems[
                        //What happens when you press ok
                    }
                });

                alergBuilder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alergBuilder.setNeutralButton(R.string.clear_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int indx) {
                        for(int i=0; i<achecked.length; i++)
                        {
                            achecked[i] = false;
                            algItems.clear();
                        }
                    }
                });

                final AlertDialog alergyDialog = alergBuilder.create();
                alergyDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alergyDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff33b5e5"));
                        alergyDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ff33b5e5"));
                        alergyDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#ff33b5e5"));
                    }
                });

                alergyDialog.show();
            }
        });


        disbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder disBuilder = new AlertDialog.Builder(preferencesPage.this);
                disBuilder.setTitle("Dislikes");
                disBuilder.setMultiChoiceItems(dislike, dischecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean ischecked) {
                        if(ischecked)
                        {
                            if(!disItems.contains(position))
                            {
                                disItems.add(position);
                            }
                            else
                            {
                                disItems.remove(position);
                            }
                        }
                    }
                });

                disBuilder.setCancelable(false);
                disBuilder.setPositiveButton(R.string.done_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Hand what happens at the end of the selection.
                        //Referrence items dietItems[
                        //What happens when you press ok.
                    }
                });

                disBuilder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                disBuilder.setNeutralButton(R.string.clear_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for(int i=0; i<dischecked.length; i++)
                        {
                            dischecked[i] = false;
                            disItems.clear();
                        }
                    }
                });

                final AlertDialog disDialog = disBuilder.create();
                disDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        disDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff33b5e5"));
                        disDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ff33b5e5"));
                        disDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#ff33b5e5"));
                    }
                });
                disDialog.show();
            }
        });

    }
}
