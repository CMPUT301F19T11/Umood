package com.example.umood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is MoodHistoryActivity
 *      This activity is to display your mood Event history.
 *      You can also view more detailed information about your mood event by click this event card.
 *
 * Corresponding Backlog:
 *      - US 04.01.01: As a participant, I want to view as a list my mood history, sorted by date and time,
 *      in reverse chronological order (most recent coming first).
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class MoodHistory extends AppCompatActivity {
    private MoodAdapter adapter;
    private ArrayList<Mood> moodList;
    private SwipeMenuListView listView;
    User user;
    private static final String TAG = "qian-MoodHistory";
    private static final String TAG2 = "py";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "0");
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.mood_history);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        moodList = user.getMoodHistory();
        Collections.sort(moodList);

        final RecyclerView recyclerView = findViewById(R.id.history_recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoodAdapter(moodList);

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        ImageButton cancelButton = findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Spinner socialSituation = findViewById(R.id.spinner2);

        socialSituation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String emotion = ((TextView)view).getText().toString();

                if(!emotion.equals("All")){
                    ArrayList<Mood> moodList_Filtered = new ArrayList<Mood>();
                    for(Mood mood : moodList){
                        if(mood.getEmotion().equals(emotion)){
                            moodList_Filtered.add(mood);
                        }
                    }

                    adapter = new MoodAdapter(moodList_Filtered);
                }else{
                    adapter = new MoodAdapter(moodList);
                }

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}

/*
Code below is wrote by PY(Swipe List View):
        adapter = new MoodAdapter(this, R.layout.content, moodList);

        listView = (SwipeMenuListView) findViewById(R.id.moodListView);
        listView.setAdapter(adapter);
        Log.d(TAG, "3");

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                //create delete item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                //set delete background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0x09,0xE6,0xF0)));
                //set delete width
                deleteItem.setWidth(300);
                //set delete title
                deleteItem.setTitle("DELETE");
                //set delete title fontSize
                deleteItem.setTitleSize(18);
                //set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                //add to
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);

                listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //Log.d(TAG,"onMenuClick: clicked item "+ index);
                        //int c = (int) click;
                        Log.d(TAG2, "hah" + position);
                        moodList.remove(moodList.get(position));
                        adapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        listView.setAdapter(adapter);
 */

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                click = adapter.getItemId(i);
            }
        });
         */
