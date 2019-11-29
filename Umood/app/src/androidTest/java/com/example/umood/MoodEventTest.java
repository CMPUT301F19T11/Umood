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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class MoodEventTest {
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
        solo.enterText((EditText) solo.getView(R.id.username), "qiantest");
        solo.clickOnView(solo.getView(R.id.cancel2));
    }

    /**
     *  Involved Backlog:
     *  US 01.01.01
     *      - As a participant, I want to add a mood event to my mood history, each event with
     *          1. the current date,
     *          2. time,
     *          3. a required emotional state,
     *          4. optional reason,
     *          5. and optional social situation.
     */
    @Test
    public void SearchRequiredAttributes(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));

        assertTrue(solo.searchText("Time"));
        assertTrue(solo.searchText("Date"));
        assertTrue(solo.searchText("Reason"));
        assertTrue(solo.searchText("Social Situation"));
        assertTrue(solo.searchText("Location"));
    }
    /**
     * US 02.03.01
     *      - As a participant, I want to specify the social situation for a mood event to be one of:
     *          1. alone,
     *          2. with one other person,
     *          3. with two to several people,
     *          4. with a crowd.
     */
    @Test
    public void SearchRequiredSocialSituation(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));

        solo.pressSpinnerItem(0,0);
        assertTrue(solo.isSpinnerTextSelected(0, ""));

        solo.pressSpinnerItem(0,1);
        assertTrue(solo.isSpinnerTextSelected(0, "Alone"));


        solo.pressSpinnerItem(0,1);
        assertTrue(solo.isSpinnerTextSelected(0, "Along with one person"));

        solo.pressSpinnerItem(0,1);
        assertTrue(solo.isSpinnerTextSelected(0, "Along with two to several persons"));

        solo.pressSpinnerItem(0,1);
        assertTrue(solo.isSpinnerTextSelected(0, "With a Crowd"));

    }

    /**
     * Involved Backlog:
     *  US 02.01.01
     *      - As a participant, I want to express the reason why for a mood event using
     *        a brief textual explanation (no more than 20 characters or 3 words).
     */
    @Test
    public void ReasonTest(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        solo.sleep(1000);

        // More than 20 characters ==> a warning pops up
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "floccinaucinihilipilification");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.save_button));
        assertTrue(solo.waitForText("Reason has to be less than 20 characters or 3 words!",1,2000));

        solo.sleep(1000);

        // More than 3 words ==> a warning pops up
        solo.clearEditText((EditText) solo.getView(R.id.reason_text3));
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "I am fine, thanks");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.save_button));
        assertTrue(solo.waitForText("Reason has to be less than 3 words!"));
    }

    /**
     * Involved Backlog:
     *  US 01.03.01
     *      - As a participant, I want to view a given mood event and all its available details.
     *  US 01.05.01
     *      - As a participant, I want to delete a given mood event of mine.
     */
    @Test
    public void ViewEditAndDeleteMoodEvent() {
        String emotion1 = "Happy";
        String emotion2 = "happy";
        String currentDate;
        String currentTime;
        String ss = "With a Crowd";
        String reason = "TestMoodEvent1";

        // Add a Mood Event
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));

        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));

        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), reason);
        solo.pressSpinnerItem(0,4);
        boolean actual = solo.isSpinnerTextSelected(0, ss);
        assertTrue("spinner item With a Crowd is not selected", actual);
        TextView Date = solo.getCurrentActivity().findViewById(R.id.reason_text2);
        currentDate =
                Date.getText().toString();

        TextView Time = solo.getCurrentActivity().findViewById(R.id.reason_text);
        currentTime =
                Time.getText().toString();

        //solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(1000);
        solo.waitForActivity(MainActivity.class, 2000);




        // View Detail
        solo.clickOnText("Profile");
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.waitForActivity(MoodHistory.class, 2000);
        solo.sleep(500);

        // RecyclerView recyclerView = solo.getCurrentActivity().findViewById(R.id.history_recycle_view);
        // solo.clickOnView(recyclerView.getChildAt(0));
        solo.clickOnText(currentTime);
        solo.waitForActivity(DetailMoodActivity.class, 2000);

        assertTrue(solo.searchText(emotion1) || solo.searchText(emotion2));
        assertTrue(solo.searchText(currentDate));
        assertTrue(solo.searchText(currentTime));
        assertTrue(solo.searchText(ss));

        // Edit it
        reason = "Edit Test";
        ss = "Along with one person";
        solo.clearEditText((EditText) solo.getView(R.id.reason_text3));
        solo.enterText((EditText) solo.getView(R.id.reason_text3), reason);
        solo.pressSpinnerItem(0,-2);
        assertTrue(solo.isSpinnerTextSelected(0, ss));
        solo.clickOnView(solo.getView(R.id.save_button));
        assertTrue(solo.waitForActivity(MainActivity.class, 2000));
        solo.sleep(500);


        solo.clickOnText("Profile");
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.waitForActivity(MoodHistory.class, 2000);
        solo.sleep(500);
        solo.clickOnText(currentTime);

        solo.waitForActivity(DetailMoodActivity.class, 2000);
        assertTrue(solo.searchText(emotion1) || solo.searchText(emotion2));
        assertTrue(solo.searchText(ss));
        assertTrue(solo.searchText(reason));


        // Delete it
        solo.clickOnView(solo.getView(R.id.save_button2));
        assertTrue(solo.waitForActivity(MainActivity.class, 2000));

        // Check if it is deleted
        solo.clickOnText("Profile");
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.waitForActivity(MoodHistory.class, 2000);
        assertFalse((solo.searchText(currentDate) && solo.searchText(currentTime))); // Date + time can be the id for a specific mood event

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
