package com.example.umood;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.umood.ui.home.HomeFragment;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class addMoodInfoTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<addMoodInfo> rule = new ActivityTestRule<>(addMoodInfo.class, true, true);

    @Before
    public void setUp(){
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());

    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();

    }
    /**
     *Test addMoodInfo
     * click happy
     */
    @Test
    public void checkHappy() {
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        solo.clickOnImageButton(0);
        solo.clickOnButton("Finish");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

    }
    /**
     *Test addMoodInfo
     * click Scared
     */
    @Test
    public void checkScared() {
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        solo.clickOnImageButton(3);
        solo.clickOnButton("Finish");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

    }
    /**
     *Test addMoodInfo
     * click Sick
     */
    @Test
    public void checkSick() {
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        solo.clickOnImageButton(2);
        solo.clickOnButton("Finish");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

    }
    /**
     *Test addMoodInfo
     * click Angry
     */
    @Test
    public void checkAngry() {
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        solo.clickOnImageButton(1);
        solo.clickOnButton("Finish");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

    }



    @After
    public void tearDown(){
        solo.finishOpenedActivities();
    }

}
