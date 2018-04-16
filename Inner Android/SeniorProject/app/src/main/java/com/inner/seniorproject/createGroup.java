package com.inner.seniorproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;



public class createGroup extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private TextView timePickerValueTextView;
    private TextView grpname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        dateView = (TextView) findViewById(R.id.date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        timePickerValueTextView = (TextView)findViewById(R.id.time);
        grpname = (TextView) findViewById(R.id.groupname);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        this.showTimePickerDialog();
    }

    //Date picker stuff
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));
    }


    // Create and show a TimePickerDialog when click button.
    private void showTimePickerDialog()
    {
        // Get open TimePickerDialog button.
        ImageButton timePickerDialogButton = (ImageButton)findViewById(R.id.timebtn);
        timePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new OnTimeSetListener instance. This listener will be invoked when user click ok button in TimePickerDialog.
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append(hour);
                        strBuf.append(":");
                        strBuf.append(minute);


                        timePickerValueTextView.setText(strBuf.toString());
                    }
                };

                Calendar now = Calendar.getInstance();
                int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = now.get(java.util.Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = false;

                TimePickerDialog timePickerDialog = new TimePickerDialog(createGroup.this, onTimeSetListener, hour, minute, is24Hour);

                //timePickerDialog.setIcon(R.drawable.if_snowman);
                timePickerDialog.setTitle("Please select time.");

                timePickerDialog.show();
            }
        });
    }

    public void goTofod(View view)
    {
        boolean canGoforward = true;
        if(dateView.getText().toString().equals(""))
        {
            //Toast for the date
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Date!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 890);
            toast.show();
            canGoforward = false;
        }
        if(timePickerValueTextView.getText().toString().equals(""))
        {
            //Toast for time
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Time!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 1040);
            toast.show();
            canGoforward = false;
        }
        if(grpname.getText().toString().equals(""))
        {
            //Toast for groupName
            Toast toast = Toast.makeText(getApplicationContext(), "Enter GroupName!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 1240);
            toast.show();
            canGoforward = false;
        }
        if(canGoforward) {
            Intent intent = new Intent(this, foodSwipe.class);
            startActivity(intent);
        }
    }



}

