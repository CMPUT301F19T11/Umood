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
        solo.clickOnView(solo.getView(R.id.cancel));
        solo.sleep(5000);

    }


    /**
     * This function test if the user arrives MoodHistory page when he/she clicks on MOOD HISTORY button.
     */

    @Test
    public void checkMoodHistoryButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);
    }

    /**
     * This function test if the user arrives MoodStatistics page when he/she clicks on MOOD STATISTICS button.
     */

    @Test
    public void checkMoodStatisticsButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_chart));
        solo.sleep(5000);
        solo.waitForActivity(ChartActivity.class, 2000);
        //solo.clickOnView(solo.getView(R.id.button22));
        //solo.sleep(2000);


    }

    /**
     * This function test if the user arrives Feed page when he/she clicks on FEED button.
     */

    @Test
    public void checkFeedButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_feed));
        solo.sleep(5000);
        solo.waitForActivity(FeedActivity.class, 2000);

    }

    /**
     * This function test if the user arrives Setting page when he/she clicks on SETTINGS button.
     */

    @Test
    public void checkSettingsButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_setting));
        solo.sleep(5000);
        solo.waitForActivity(SettingActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.switch1));
        solo.clickOnView(solo.getView(R.id.switch2));
        solo.clickOnView(solo.getView(R.id.apply));
        solo.sleep(6000);
        solo.waitForFragmentById(R.id.navigation_notifications);


    }

    /**
     * This function test if the user arrives friend page when he/she clicks on friends button.
     */

    @Test
    public void checkFriendButtonFromProfile(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_dashboard));
        solo.waitForFragmentById(R.id.navigation_dashboard);
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
