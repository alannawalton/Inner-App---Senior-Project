package com.inner.seniorproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class foodSwipe extends AppCompatActivity {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> array;
    private SwipeFlingAdapterView flingContainer;
    public HashMap<String, Double> restaurants;
    private HashMap<String, Integer> resMax;
    private HashMap<String, String> locations;
    int busboysMax = 10*3;
    int bensMax = 7*3;
    int leDiMax = 8 * 3;
    int ticoMax = 8*3;
    int tedMax = 9*3;
    int pizMax = 8*3;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String grpName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_swipe);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        Intent intent = getIntent();
        grpName = (String) intent.getStringExtra("GroupName");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        restaurants = new HashMap<>();
        resMax = new HashMap<>();
        locations = new HashMap<>();
        restaurants.put("Busboys and Poets", 0.0);
        resMax.put("Busboys and Poets", busboysMax);
        locations.put("Busboys and Poets", "2021 14th St NW,\n Washington, DC 20009");
        restaurants.put("Ben’s Chili Bowl", 0.0);
        resMax.put("Ben’s Chili Bowl", bensMax);
        locations.put("Ben’s Chili Bowl", "1213 U St NW,\n Washington, DC 20009");
        restaurants.put("Le Diplomate", 0.0);
        resMax.put("Le Diplomate", leDiMax);
        locations.put("Le Diplomate", "1601 14th St NW\n Washington, DC 20009");
        restaurants.put("Tico DC", 0.0);
        resMax.put("Tico DC", ticoMax);
        locations.put("Tico DC", "1926 14th St NW\n Washington, DC 20009");
        restaurants.put("Ted’s Bulletin", 0.0);
        resMax.put("Ted’s Bulletin", tedMax);
        locations.put("Ted’s Bulletin", "1818 14th St NW\n Washington, DC 20009");
        restaurants.put("&pizza", 0.0);
        resMax.put("&pizza", pizMax);
        locations.put("&pizza", "1250 U St NW\n Washington, DC 20009");

        array = new ArrayList<>();
        //Hard Coding the Data for Project Presentation.
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/sc69WoN04j2TI-1zvl9YUw/o.jpg", "Blackened Salmon",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/k4qgwNCJ9rQNc2noErYGIA/o.jpg", "Chili Dogs n Chips", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/4WoGmX0C0_B4M54r_4Pq2g/o.jpg", "Mushroom Tart", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/s0K0y6Ka7lkKy_NQXA2vuw/o.jpg", "Crispy Manchego Cheese", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/mvZFN46KNQqz19I8qfQXsQ/o.jpg", "Four Layer Carrot Cake", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/d-NAiTeQTTAUmS7Pvgf5iA/o.jpg", "French Toast and Turkey Sausage Breakfast",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/lTFqS9W5KlUA8Z8CZ6yahw/o.jpg", "Chili Cheese Fries", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/3Ql6YzuAt6EwyKEyHZ3tNQ/o.jpg", "Maverick", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/vdBDhbkJOlGdPG4SA0Wu1w/o.jpg", "Vanilla Bean Creme Brulee", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/diESk12ZpXMJDkzmFOVwow/o.jpg", "Chocolate Tart", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/vmlUpcMbPuZno1xysM_yIQ/o.jpg", "Mekhleme",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media1.fl.yelpcdn.com/bphoto/tpwlixTVaNgwn5iGjFgfcg/o.jpg", "Beef Chili Dog", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/DMppEUPDE2UUq4lXj5S5Pw/o.jpg", "Gnarlic", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/XLMh7f4eMwrLKU6R-UwWEQ/o.jpg", "Home Fries", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/o-lgCDe4K41PRohIIuZm3Q/o.jpg", "Open face omelette", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/Q0M6k7kiGwTxXZzSCVRDGQ/o.jpg", "Cinnamon Brown Sugar Ted Tart", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media1.fl.yelpcdn.com/bphoto/2JXeAM92d8VNekusRgQYYg/o.jpg", "Sweet Potato Pancakes Organic Eggs Turkey Bacon and Fruit",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/5eJ-W7xeiT8z2uwj39-Ovw/o.jpg", "Half smoke", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/CrFqYHCVN52LHP8_OG0vmw/o.jpg", "Farmer's Daughter", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/eOZJ_SMh-BHdAP2XnJhTfA/o.jpg", "Duck Confit", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/A4L0P4B9hc2uwQI_vZW7GQ/o.jpg", "Sides of Potatoes and Applewood Bacon", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/xAgOUwqAyfnM79CamoDF4A/o.jpg", "Vanilla Cruller", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/jg2osKq-xvTBuHIVlB0Pgw/o.jpg", "Vegan Tofu Scramble",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/l_qRbPqPq5GcGlMvZ4cmFA/o.jpg", "Chili bowl", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/Zb4iJXCNFvwvkCOxbcaLSQ/o.jpg", "Moonstruck", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/4-e-c-7hBT8s-KlTg-lyIw/o.jpg", "Steak and Eggs", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media1.fl.yelpcdn.com/bphoto/oQ8jHc7zA6GvU2qOQ8zskg/o.jpg", "Pancakes and Bananas", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/cMTtgWH8EziOGfzXIXCEdQ/o.jpg", "Chicken 'n Biscuits", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/z8NbxYZSzeevz0vcuKiSVw/o.jpg", "Crab Cake Sandwich, Harira Soup",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/ArOIdgrdyTT89DFKZBLbSw/o.jpg", "Hamburger with Chili on the Side", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/KGGO_clawGpzQf0cJN2fhQ/o.jpg", "Salad Pizza", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/bAVMB_FTm9Luq2-xezhPCA/o.jpg", "Eggs Vol Au Vent and Turkey Sausage", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/2Xnw77DTelpNc-axC4Vdmg/o.jpg", "Spicy Corn with Jalapeños", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/TWfsKIF8THqG4kK6v7lKnw/o.jpg", "Oreo Milkshake", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/yPbHxaAq6R3Qj17kz_ZJnA/o.jpg", "The Popeye", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/LxcN6D80PN61pKeDkIt-nQ/o.jpg", "Sweet Potato Hash",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media1.fl.yelpcdn.com/bphoto/vN385LLG3ctZRdbZS59IuA/o.jpg", "Chocolate Shake", "Ben’s Chili Bowl", "1213 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media1.fl.yelpcdn.com/bphoto/jl04kENyYTuuSvvaVh5t5Q/o.jpg", "Lori Lane", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/D-ILlY-EJi-JTpsFtrhNqA/o.jpg", "Steak Frites", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/V5R3oi9YmwEYdZLigwXHBQ/o.jpg", "Pork Belly Sliders", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/Ho2MaMP4XvjyfA3seSog4w/o.jpg", "Corned Beef Hash", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media3.fl.yelpcdn.com/bphoto/QEQawfNAh-gFwNrLhGsFuA/o.jpg", "Shrimp, Chicken and Chorizo Pasta",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/iyJzy0MJrMC82kfB44amfw/o.jpg", "Thai + Italian Smorgasbord", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media1.fl.yelpcdn.com/bphoto/OFIi_GnVN1sCeCiueCN7-g/o.jpg", "Burger American", "Le Diplomate", "1601 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/aTsoYUXmtVfmVlgiyztfkg/o.jpg", "Tico's Mac 'n Cheese", "Tico DC", "1926 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/l8eZWp4Vvv9AqinQPq6jiQ/o.jpg", "Tri Tip Steak, Brussels Sprouts with Blue Cheese", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/APSxGki_eXCm7KRijxW5IA/o.jpg", "Mushroom and Pepper Jack Burger",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/7SLqoB0BhhmgG6olgCZa0A/o.jpg", "Custom Pizza", "&pizza", "1250 U St NW Washington, DC 20009"));
        array.add(new Data("https://s3-media4.fl.yelpcdn.com/bphoto/U7bmS5-OQvnAM9n8ilz9ZQ/o.jpg", "Portobello Mozzarella Panini",  "Busboys and Poets", "2021 14th St NW, Washington, DC 20009"));
        array.add(new Data("https://s3-media2.fl.yelpcdn.com/bphoto/VNUFAIZOrz3q30jzKvQkYw/o.jpg", "Egg White Omelette with Corned Beef", "Ted’s Bulletin", "1818 14th St NW Washington, DC 20009"));


        myAppAdapter = new MyAppAdapter(array, this);
        flingContainer.setAdapter(myAppAdapter);
        //final View view =
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Data temp = array.get(0);
                Double score = restaurants.get(temp.getCompany());
                score -= 3;
                restaurants.put(temp.getCompany(), score);
                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                if(array.size() == 0)
                {
          //          gotoSelectfood();
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Data temp = array.get(0);
                Double score = restaurants.get(temp.getCompany());
                score += 3;
                restaurants.put(temp.getCompany(), score);

                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText("Name of Dish: " + parkingList.get(position).getName() + "\nRestaurant: " + parkingList.get(position).getCompany() +
                                        "\nLocation: " + parkingList.get(position).getAddress());

            Glide.with(foodSwipe.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }

    public void gotoSelectfood(View view)
    {
        Set set = restaurants.entrySet();
        Iterator i = set.iterator();

        FirebaseUser user = mAuth.getCurrentUser();
        //Calling the MyUsers anc creatign a child by the name of this user.
        DatabaseReference groupName = databaseReference.child(grpName);
        DatabaseReference usrName = groupName.child(user.getDisplayName());

        while (i.hasNext())
        {
            Map.Entry entry = (Map.Entry)i.next();
            Double score = (Double) entry.getValue();
            score = (Double) score/resMax.get(entry.getKey()) * 100;
            if(score == -100.0) {
                score = -105.0;
            }
            entry.setValue(score);

            DatabaseReference resName = usrName.child(entry.getKey().toString());
            resName.setValue(score);
        }
        Intent intent = new Intent(this, selectRes.class);
        intent.putExtra("map", restaurants);
        intent.putExtra("data", locations);
        intent.putExtra("GroupName", grpName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    //For the purpose of the School Project I am going to put all Pictures from Companies here this week
    //Also will have a hashtable with all the keys as the company name and value score initalized to 0;

}

