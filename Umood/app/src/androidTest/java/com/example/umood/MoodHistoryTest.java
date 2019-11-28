package com.example.umood;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

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
     *
     * US 04.01.01
     * As a participant, I want to view as a list my mood history, sorted by date and time, in reverse chronological order (most recent coming first).
     */
    @Test
    public void TestMoodHistoryList(){
        //Add new mood event1.
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        //solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "new test");
        solo.pressSpinnerItem(0,4);
        boolean actual = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);



        //Add new mood event3.
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.sickButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        //solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "new test2");
        solo.pressSpinnerItem(0,4);
        boolean actual3 = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual3);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);

        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(3000);
        solo.waitForActivity(MoodHistory.class, 2000);

        final RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.history_recycle_view);
        assertTrue(recyclerView.getChildCount()> 0);


    }

    /**
     * This function tests that if the filter spinner works.
     * US 04.02.01
     * As a participant, I want to filter my mood history list to show only mood events with a particular emotional state.
     */

    @Test
    public void TestFilter(){
        //Add new mood event1.
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        //solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "new test");
        solo.pressSpinnerItem(0,4);
        boolean actual = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);


        //Add new mood event2.
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        //solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "new test1");
        solo.pressSpinnerItem(0,1);
        boolean actual2 = solo.isSpinnerTextSelected(0, "Alone");
        assertTrue("spinner item Alone is not selected", actual2);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);


        //Add new mood event3.
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.sickButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        //solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "new test2");
        solo.pressSpinnerItem(0,4);
        boolean actual3 = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual3);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);


        // Filter the Mood events.
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
        assertTrue("spinner item Scared is not selected", solo.isSpinnerTextSelected(0, "Scared"));
        solo.sleep(4000);
        solo.pressSpinnerItem(0,-4);
        assertTrue("spinner item All is not selected", solo.isSpinnerTextSelected(0, "All"));
        solo.sleep(4000);


    }

    /**
     * This functions test if the user go back to the profile page when clicking on CANCEL button.
     */

    @Test
    public void TestCancelButton(){
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
