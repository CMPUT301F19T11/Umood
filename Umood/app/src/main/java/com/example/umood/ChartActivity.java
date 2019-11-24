package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;


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
        //getSupportActionBar().hide();

        setContentView(R.layout.pie_chart);

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
//        l.setForm(Legend.LegendForm.LINE);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);


        // entry label styling
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);

//        pieChart.setOnChartValueSelectedListener(this);
        pieChart.animateY(3400, Easing.EasingOption.EaseInQuad);
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        pieEntries.add( new PieEntry(24,"Angry"));
        pieEntries.add( new PieEntry(25,"Happy"));
        pieEntries.add( new PieEntry(28,"Sad"));
        pieEntries.add( new PieEntry(22,"Sick"));

        String centerText = "Mood Statistic";
        pieChart.setCenterText(centerText);
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // PieChart Color
        colors.add(Color.rgb(227, 23, 13));
        colors.add(Color.rgb(255, 227, 132));
        colors.add(Color.rgb(135, 206, 235));
        colors.add(Color.rgb(0, 201, 87));

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
