package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;


/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This One Is An Extra Feature
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.pie_chart);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");
        ArrayList<Mood> history = user.getMoodHistory();

        SegmentedGroup segmented2 = (SegmentedGroup) findViewById(R.id.segmented2);
        Button buttonPieChart = findViewById(R.id.button21);


        int scared = 0;
        int sick = 0;
        int happy = 0;
        int angry = 0;

        if(!history.isEmpty()) {
            for (Mood mood : history) {
                String e = mood.getEmotion();
                switch (e) {
                    case "Happy":
                        happy += 1;
                        break;
                    case "Scared":
                        scared += 1;
                        break;
                    case "Angry":
                        angry += 1;
                        break;
                    default:
                        sick += 1;
                }
            }
        }

        pieChart= (PieChart) findViewById(R.id.consume_pie_chart);

        pieChart.setUsePercentValues(true);
        pieChart.setDescription("PieChart");
        pieChart.setDescriptionTextSize(10);

        pieChart.setExtraOffsets(5,5,5,5);

        pieChart.setDrawCenterText(true);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setCenterTextSize(15);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(30f);

        pieChart.setTransparentCircleColor(Color.BLACK);
        pieChart.setTransparentCircleAlpha(100);
        pieChart.setTransparentCircleRadius(30f);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setRotationAngle(10);

        pieChart.setHighlightPerTapEnabled(true);


        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);


        // entry label styling
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);

        pieChart.animateY(3400, Easing.EasingOption.EaseInQuad);
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        if(angry>0) {
            pieEntries.add(new PieEntry(angry, "Angry"));
        }
        if(happy>0) {
            pieEntries.add(new PieEntry(happy, "Happy"));
        }
        if(scared>0) {
            pieEntries.add(new PieEntry(scared, "Scared"));
        }
        if(sick>0) {
            pieEntries.add(new PieEntry(sick, "Sick"));
        }


        String centerText = "Mood Statistic";
        pieChart.setCenterText(centerText);
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // PieChart Color
        if(angry>0) {
            colors.add(Color.rgb(0xee, 73, 0x7a));
        }
        if(happy>0) {
            colors.add(Color.rgb(0xfd, 0xee, 87));
        }
        if(scared>0) {
            colors.add(Color.rgb(88, 0xc8, 0xfa));
        }
        if(sick>0) {
            colors.add(Color.rgb(76, 0xdc, 93));
        }

        pieDataSet.setColors(colors);

        pieDataSet.setSliceSpace(0f);
        pieDataSet.setSelectionShift(5f);
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);


        pieData.setValueFormatter(new PercentFormatter());//Set%
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLUE);

        pieChart.setData(pieData);
        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


}
