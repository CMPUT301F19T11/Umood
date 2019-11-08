package com.example.umood;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;

public class addMoodInfo extends AppCompatActivity {
    private static final String TAG = "qian-addMood";

    private String emotion;
    private String situation;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");

    private int RESULT_LOAD_IMAGE = 2;
    ImageButton image;
    Uri imageUri;
    Bitmap selectedImage;
    String imagePath = "";

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

        Button next = findViewById(R.id.next);
        ImageButton cancel = findViewById(R.id.add_cancel);

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

        image = findViewById(R.id.imageButton2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
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
        Cursor cursor = getContentResolver().query(imageUri ,filePathColumn, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
