package com.example.umood;

import android.widget.EditText;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.example.umood.ui.home.HomeFragment;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class MainActivityTest {
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
     * This function test if the user arrives profile page when he/she clicks on profile button.
     */

    @Test
    public void checkProfileButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);

    }

    /**
     * This function test if the user arrives friend page when he/she clicks on friends button.
     */

    @Test
    public void checkFriendButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_dashboard));
        solo.waitForFragmentById(R.id.navigation_dashboard);
    }

    /**
     * This function test if switching Maps works.
     *
     * US 06.02.01
     * As a participant, I want to see a map of the mood events (showing their emotional states)
     * from my mood history list (that have locations).
     *
     * US 06.03.01
     * As a participant, I want to see a map of the mood events (showing their emotional states
     * and the username) from my mood following list (that have locations).
     */

    @Test
    public void checkSwitchMapButton(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.sickButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "test2");
        solo.pressSpinnerItem(0,4);
        boolean actual = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.mapView));
        solo.clickOnView(solo.getView(R.id.swapButton));
        solo.sleep(4000);
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
