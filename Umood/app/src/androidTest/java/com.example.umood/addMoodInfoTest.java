/*

package com.example.umood;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class addMoodInfoTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<AddFollowingActivity> rule =
            new ActivityTestRule<>(AddFollowingActivity.class,true,true);
    @Before
    public void setUp(){
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());

    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();

    }

    @Test
    public void checkHappy(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        solo.clickOnImageButton(R.drawable.happy);
        solo.clickOnButton("next");

        //solo.assertCurrentActivity("Wrong Activity",);

    }




    @After
    public void tearDown(){
        solo.finishOpenedActivities();
    }


}
*/