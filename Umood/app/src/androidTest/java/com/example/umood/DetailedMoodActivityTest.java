package com.example.umood;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.umood.ui.home.HomeFragment;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DetailedMoodActivityTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<DetailMoodActivity> rule = new ActivityTestRule<>(DetailMoodActivity.class, true, true);

    @Before
    public void setUp() {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());

    }
/*
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();

    }
/*
    /*
    @Test
    public void checkMoodDetail(){
        solo.assertCurrentActivity("Wrong Activity",DetailMoodActivity.class);
        //solo.enterText((EditText)solo.getView(R.id.reason_text2),"11:48:52");
        //solo.enterText((EditText)solo.getView(R.id.reason_text),"2019-11-08");
        //solo.enterText((EditText)solo.getView(R.id.reason_text3),"no reason");
        solo.clickOnButton("Delete");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

    }

     */

}