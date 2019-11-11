package com.example.umood;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class addMoodInfo extends AppCompatActivity {
    private static final String TAG = "qian-addMood";

    private String emotion;
    private String situation;
    private double longitude;
    private double latitude;

    Geocoder geocoder;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private int RESULT_LOAD_IMAGE = 2;
    private FusedLocationProviderClient fusedLocationClient;

    private TextView cityNameView;
    private TextView addressNameView;
    private ImageButton image;
    private Uri imageUri;
    private Bitmap selectedImage;
    private String imagePath = "";
    private ImageButton geoMap;
    private addMoodInfo activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Log.d(TAG,"in 1");
        setContentView(R.layout.emotional_state);

        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sick = findViewById(R.id.sickButton);
        ImageButton angry = findViewById(R.id.angryButton);
        ImageButton scared = findViewById(R.id.scaredButton);

        addressNameView = findViewById(R.id.textView11);
        image = findViewById(R.id.imageButton2);
        cityNameView = findViewById(R.id.textView10);
        geocoder = new Geocoder(this, Locale.getDefault());
        geoMap = findViewById(R.id.imageButton4);


        Button next = findViewById(R.id.next);
        ImageButton cancel = findViewById(R.id.add_cancel);


        // 4 emotions:
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Happy";
            }
        });
        sick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Sick";

            }
        });
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Angry";
            }
        });
        scared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Scared";

            }
        });
        Spinner socialSituation = findViewById(R.id.spinner2);

        // Location Part:
        geoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] permissions = new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                Log.d(TAG, "pos1");
                if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {

                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
                    Log.d(TAG, "pos2");
                    fusedLocationClient.getLastLocation()
                            .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    // Got last known location. In some rare situations this can be null.
                                    Log.d(TAG, "pos3");
                                    if (location != null) {
                                        // Logic to handle location object
                                        Log.d(TAG, "pos3");
                                        try {
                                            Log.d(TAG, "pos3");
                                            longitude = location.getLongitude();
                                            latitude = location.getLatitude();
                                            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                            String cityName = addresses.get(0).getLocality();
                                            String address = addresses.get(0).getThoroughfare();
                                            cityNameView.setText(cityName);
                                            addressNameView.setText(address);
                                            Log.d(TAG, address);
                                            geoMap.setImageResource(R.drawable.place);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            })
                            .addOnFailureListener(activity, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: my bad");
                                }
                            });
                }
                else{
                    getPermission(Manifest.permission.ACCESS_FINE_LOCATION,permissions);
                }

            }
        });





        // Image Part:
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] permissions = new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };
                if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
                }
                else{
                    getPermission(Manifest.permission.READ_EXTERNAL_STORAGE,permissions);
                }
            }
        });


        socialSituation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                situation = ((TextView)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emotion == null){
                    Toast.makeText(getBaseContext(), "Choose an emotion!", Toast.LENGTH_LONG).show();
                }
                else if(situation==null){
                    Toast.makeText(getBaseContext(), "Choose an Social Situation!", Toast.LENGTH_LONG).show();
                }
                else {
                    EditText reasonText = findViewById(R.id.reason_text);
                    String reason = reasonText.getText().toString();
                    if(reason.length()>20)
                        Toast.makeText(getBaseContext(), "Reason has to be less than 20 characters or 3 words!", Toast.LENGTH_LONG).show();
                    else if(testWords(reason))
                        Toast.makeText(getBaseContext(), "Reason has to be less than 3 words!", Toast.LENGTH_LONG).show();
                    else {
                        Intent intent = new Intent();
                        intent.putExtra("Reason",reason);
                        intent.putExtra("SocialSituation", situation);
                        intent.putExtra("Mood", emotion);
                        intent.putExtra("Path",imagePath);
                        intent.putExtra("Longitude",longitude);
                        intent.putExtra("Latitude",latitude);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }


    //Check Permission
    private boolean checkPermission(String permission) {
        // PackageManager.PERMISSION_GRANTED ==> The permission is granted
        return (ActivityCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED);
    }

    private void getPermission(String permission,String [] permissions) {

        //Apply
        ActivityCompat.requestPermissions(
                this,
                permissions,
                REQUEST_EXTERNAL_STORAGE);
        // If the user deny the permission
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
            Toast.makeText(this, "The app cannot show picture normally，If we don't have the permission！", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            try {
                imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                image.setImageBitmap(selectedImage);
                imagePath = getPath(imageUri);

            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri ,filePathColumn, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String val = cursor.getString(column_index);
        cursor.close();
        return val;
    }



    private boolean testWords(String s){
        int count = 0;
        for(char letter:s.toCharArray())
            if(letter==' ')
                count+=1;
        return count>2;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

}
