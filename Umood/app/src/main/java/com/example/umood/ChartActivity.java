package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This One Is An Extra Feature
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_chart);
    }
}
