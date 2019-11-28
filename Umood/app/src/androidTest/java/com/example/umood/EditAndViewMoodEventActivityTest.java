package com.example.umood;

import android.app.Activity;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class EditAndViewMoodEventActivityTest {
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
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }


    /**
     * US 01.03.01
     * As a participant, I want to view a given mood event and all its available details.
     */

    @Test
    public void ViewMoodDetail(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);
        final RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.history_recycle_view);
        solo.clickOnView(recyclerView.getChildAt(0));
        solo.sleep(2000);
    }

    /**
     * US 01.04.01
     * As a participant, I want to edit the details of a given mood event of mine.
     */

    @Test
    public void EditMoodDetail(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);
        final RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.history_recycle_view);
        solo.clickOnView(recyclerView.getChildAt(0));
        EditText text = (EditText) solo.getView(R.id.reason_text3);
        solo.clearEditText(text);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "new test");
        //solo.clickOnView(solo.getView(R.id.spinner2));
        solo.pressSpinnerItem(0,4);
        boolean actual = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual);
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MoodHistory.class, 2000);
    }


    @Test
    public void ClickCancelButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);
        final RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.history_recycle_view);
        solo.clickOnView(recyclerView.getChildAt(1));
        solo.sleep(2000);
        solo.clickOnView(solo.getView(R.id.add_cancel));
        solo.sleep(4000);
        solo.waitForActivity(MoodHistory.class, 2000);
    }

    /**
     * US 01.05.01
     * As a participant, I want to delete a given mood event of mine.
     */


    @Test
    public void ClickDeleteButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_history));
        solo.sleep(5000);
        solo.waitForActivity(MoodHistory.class, 2000);
        final RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.history_recycle_view);
        solo.clickOnView(recyclerView.getChildAt(1));
        solo.sleep(2000);
        solo.clickOnView(solo.getView(R.id.save_button2));
        solo.sleep(4000);
        solo.waitForActivity(MoodHistory.class, 2000);
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
