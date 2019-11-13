package com.example.umood;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


/*
public class MoodHistoryTest {

    private Solo solo;
    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class, true, true);
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.username),"yifan");
        solo.clickOnView(solo.getView(R.id.cancel));
        solo.waitForActivity(MainActivity.class, 2000);

    }


    @Test
    public void checkMainActivity(){
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
    }


    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }



    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
*/
