package com.inner.seniorproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InfoPage extends AppCompatActivity {

    FrameLayout myphoto;
    TextView photoUpload;
    FirebaseAuth mAuth;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        mAuth = FirebaseAuth.getInstance();
        myphoto = (FrameLayout) findViewById(R.id.Photoframe);
        photoUpload = (TextView) findViewById(R.id.Uploadphoto);

        photoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo = imageBitmap;
            Drawable dr = new BitmapDrawable(getResources(), imageBitmap);
            myphoto.setBackground(dr);
        }
    }

    public void goToPro(View view) throws IOException {

        EditText name = (EditText) findViewById(R.id.Name);
        EditText num = (EditText) findViewById(R.id.number);

        FirebaseUser user = mAuth.getCurrentUser();
        Uri propic = (Uri) geturi(photo);
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name.getText().toString()).setPhotoUri(propic).build();
        Log.v("Sign UP page", "DO I get past storing name and pic");

        user.updateProfile(userProfileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            //Put a log message.
                            Toast.makeText(InfoPage.this, "Success with Picture", Toast.LENGTH_SHORT).show();
                            try {
                                TimeUnit.SECONDS.sleep(4);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            Toast.makeText(InfoPage.this, "It failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    Uri geturi (Bitmap bitmap) throws IOException {
        //File tempDir= Environment.getExternalStorageDirectory();
        File tempDir = this.getCacheDir();
        tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
        tempDir.mkdir();
        File tempFile = File.createTempFile("propic", ".jpg", tempDir);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] bitmapData = bytes.toByteArray();

        //write the bytes in file
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(bitmapData);
        fos.flush();
        fos.close();
        return Uri.fromFile(tempFile);
    }
}
