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

import static junit.framework.TestCase.assertTrue;

public class MoodHistoryTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class, true, true);
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
     * This function tests that if the filter spinner works.
     * US 04.01.01
     * As a participant, I want to view as a list my mood history, sorted by date and time, in reverse chronological order (most recent coming first).
     *
     * US 04.02.01
     * As a participant, I want to filter my mood history list to show only mood events with a particular emotional state.
     */

    @Test
    public void checkFilter(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);


        solo.pressSpinnerItem(0,1);
        boolean actual1 = solo.isSpinnerTextSelected(0, "Happy");
        assertTrue("spinner item Happy is not selected", actual1);
        solo.sleep(4000);
        solo.pressSpinnerItem(0,2);
        boolean actual2 = solo.isSpinnerTextSelected(0, "Scared");
        assertTrue("spinner item Scared is not selected", actual2);
        solo.sleep(4000);
        //solo.pressSpinnerItem(0,0);
        //assertTrue("spinner item All is not selected", solo.isSpinnerTextSelected(0, "All"));
        //solo.sleep(4000);


    }

    /**
     * This functions test if the user go back to the profile page when clicking on CANCEL button.
     */

    @Test
    public void CancelButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);
        solo.clickOnView(solo.getView(R.id.cancel));
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
