package com.example.umood;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test class for AddFollowingActivity. All the UI tests are written here. Robotium test framework is
 used
 */
public class AddFollowingActivityTest {
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






    @After
    public void tearDown(){
        solo.finishOpenedActivities();
    }


}
