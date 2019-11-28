package com.example.umood;

import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ProfileTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class,true,true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
        solo.enterText((EditText) solo.getView(R.id.username), "yifan");
        solo.clickOnView(solo.getView(R.id.cancel2));
    }

    /**
     * Involved story:
     *      - US 03.01.01
     * This function test if the user arrives MoodHistory page when he/she clicks on MOOD HISTORY button.
     */
    @Test
    public void checkProfileButtons(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);

        // Unique Username
        assertTrue(solo.waitForText("yifan",1,2000));
        // Mood History
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.waitForActivity(MoodHistory.class, 2000);
        solo.clickOnView(solo.getView(R.id.cancel2));
        solo.waitForActivity(MainActivity.class, 2000);

        // Feed
        solo.clickOnView(solo.getView(R.id.button_feed));
        solo.waitForActivity(FeedActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.signup_cancel));
        solo.waitForActivity(MainActivity.class, 2000);

        // Following Request
        solo.clickOnView(solo.getView(R.id.request));
        solo.waitForActivity(FeedActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.signup_cancel2));
        solo.waitForActivity(MainActivity.class, 2000);

        // Add Following
        solo.clickOnView(solo.getView(R.id.addFollowing));
        solo.waitForActivity(FeedActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.cancel2));
        solo.waitForActivity(MainActivity.class, 2000);

        // Mood Statistics
        solo.clickOnView(solo.getView(R.id.button_chart));
        solo.waitForActivity(ChartActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.cancel2));
        solo.waitForActivity(MainActivity.class, 2000);

        // Setting
        solo.clickOnView(solo.getView(R.id.button_setting));
        solo.waitForActivity(SettingActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.switch1));
        solo.clickOnView(solo.getView(R.id.switch2));
        solo.clickOnView(solo.getView(R.id.apply));
        solo.waitForFragmentById(R.id.navigation_notifications);
    }


    /**
     * Closes the activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


}
