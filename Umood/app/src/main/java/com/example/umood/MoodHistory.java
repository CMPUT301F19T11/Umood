package com.example.umood;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class MoodHistory extends AppCompatActivity {
    private MoodAdapter adapter;
    private ArrayList<Mood> moodList;
    private SwipeMenuListView listView;
    User user;
    private static final String TAG = "qian-MoodHistory";
    private static final String TAG2 = "py";
    private long click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "0");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mood_history);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        Log.d(TAG, user.getUsername());
        moodList = user.getMoodHistory();
<<<<<<< HEAD

        adapter = new MoodAdapter(
                this,
                R.layout.content,
                moodList);
=======
        Log.d(TAG, "1");
        adapter = new MoodAdapter(this, R.layout.content, moodList);
        Log.d(TAG, "2");
>>>>>>> 94f667333a232539022dc8953407d62998ce3f6c

        listView = (SwipeMenuListView) findViewById(R.id.moodListView);
        listView.setAdapter(adapter);
<<<<<<< HEAD
=======
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

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                click = adapter.getItemId(i);
            }
        });

         */

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
>>>>>>> 94f667333a232539022dc8953407d62998ce3f6c

    }
}
