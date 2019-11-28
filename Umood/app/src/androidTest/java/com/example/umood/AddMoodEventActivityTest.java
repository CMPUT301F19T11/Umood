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

public class AddMoodEventActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<AddMoodFirstActivity> rule =
            new ActivityTestRule<>(AddMoodFirstActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }

    /**
     * This function tests add new mood event works.
     */
    @Test
    public void AddNewMood(){
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        //solo.sleep(5000);
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "test");
        //solo.clickOnView(solo.getView(R.id.spinner2));
        solo.pressSpinnerItem(0,1);
        boolean actual = solo.isSpinnerTextSelected(0, "Alone");
        assertTrue("spinner item Alone is not selected", actual);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);
        //Asserts that the current activity is the MainActivity. Otherwise, show "Wrong Activity".
        // solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }

    /**
     * This function test if the warning message appears when the user press Next button without
     * Click on one of the emotion icons.
     */

    @Test
    public void NotSelectedEmotion(){
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForText("Choose an emotion!", 1,2000));
    }

    @Test
    public void CancelButton(){
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.add_cancel)); //click on cancel button.
        solo.waitForActivity(AddMoodFirstActivity.class, 2000);
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);

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
     * Closes the activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
