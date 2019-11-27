package com.example.umood;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

import java.text.DecimalFormat;

public class MyFormatter implements AxisValueFormatter {

    private DecimalFormat mFormat;
    String[] strings;


    public MyFormatter() {

        mFormat = new DecimalFormat("###,###,##0.0");
    }

    public MyFormatter(String[] strings) {

        this.strings = strings;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        if (value == 1) {
            return "Angry";
        }
        if (value == 2) {
            return "Happy";
        }
        if (value == 3) {
            return "Scared";
        }
        if (value == 4) {
            return "Sick";
        }
        return "";
    }

    @Override
    public int getDecimalDigits() {

        return 0;
    }



}

