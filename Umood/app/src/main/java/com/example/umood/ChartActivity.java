package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

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
    private BarChart barChart;
    int scared = 0;
    int sick = 0;
    int happy = 0;
    int angry = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.pie_chart);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");
        ArrayList<Mood> history = user.getMoodHistory();


        ImageButton cancel = findViewById(R.id.cancel2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


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


        barChart = (BarChart)findViewById(R.id.consume_bar_chart);
        initPieChart();
        initBarChart();
        pieChart.setVisibility(View.VISIBLE);

        Button buttonPieChart = findViewById(R.id.button21);
        Button buttonBarChart = findViewById(R.id.button22);

        buttonPieChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pieChart.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
            }
        });

        buttonBarChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                barChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(View.GONE);
            }
        });



    }

    private void initPieChart() {
        pieChart = findViewById(R.id.consume_pie_chart);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterTextTypeface(Typeface.MONOSPACE);
        pieChart.setCenterText("Statistics");

        pieChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // chart.spin(2000, 0, 360);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
        setDataPieChart();

    }


    private void setDataPieChart(){

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
            colors.add(Color.rgb(0xf1, 94, 0x9a));
        }
        if(happy>0) {
            colors.add(Color.rgb(0xff, 0xf7, 0x9a));
        }
        if(scared>0) {
            colors.add(Color.rgb(0xa1, 0xe9, 0xfc));
        }
        if(sick>0) {
            colors.add(Color.rgb(0xd2, 0xfc, 0x9a));
        }
        pieDataSet.setColors(colors);
        pieDataSet.setValueLinePart1OffsetPercentage(80.f);
        pieDataSet.setValueLinePart1Length(0.2f);
        pieDataSet.setValueLinePart2Length(0.4f);
        //dataSet.setUsingSliceColorAsValueLineColor(true);

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


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



    private void initBarChart() {

        barChart = findViewById(R.id.consume_bar_chart);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTypeface(Typeface.MONOSPACE);

        barChart.getAxisLeft().setDrawGridLines(false);

        // add a nice and smooth animation
        barChart.animateY(1500);

        barChart.getLegend().setEnabled(false);

        //axisLeft.setValueFormatter(new MyFormatter2());
        barChart.animateY(1500);
        barChart.setDescription("");



        int max = angry;
        if(max < scared){
            max = scared;
        }
        if(max < sick){
            max = sick;
        }
        if(max < happy){
            max = happy;
        }
        int total = angry + scared + sick + happy;
        if(total > 2*max) {
            total = 2 * max;
        }
        barChart.getAxisRight().setEnabled(false);

        //set data
        setData01();

    }


    private void setData01() {
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        yVals1.add(new BarEntry(1, angry));
        yVals1.add(new BarEntry(2, happy));
        yVals1.add(new BarEntry(3, scared));
        yVals1.add(new BarEntry(4, sick));

        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "");
        //set color
        //set1.setColor(Color.parseColor("#4169E1"));
        set1.setColor(Color.rgb(0xf1, 94, 0x9a));
        set1.addColor(Color.rgb(0xff, 0xf7, 0x9a));
        set1.addColor(Color.rgb(0xa1, 0xe9, 0xfc));
        set1.addColor(Color.rgb(0xd2, 0xfc, 0x9a));



        set1.setDrawValues(false);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.setFitBars(true);
        //set text size
        set1.setValueTextSize(12f);
        //set width
        data.setBarWidth(0.7f);
        barChart.invalidate();

        for (IDataSet set : barChart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        barChart.invalidate();
        barChart.setAutoScaleMinMaxEnabled(!barChart.isAutoScaleMinMaxEnabled());
        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }


}
