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
     * This function tests add new mood event works.
     *
     * US 01.01.01
     * As a participant, I want to add a mood event to my mood history,
     * each event with the current date and time, a required emotional state, optional reason,
     * and optional social situation.
     *
     * US 01.02.01
     * As a participant, I want consistent emoticons and colors to depict and distinguish
     * the emotional states in any view.
     *
     *
     * US 02.01.01
     * As a participant, I want to express the reason why for a mood event using a brief textual
     * explanation (no more than 20 characters or 3 words).
     *
     * US 02.02.01
     * As a participant, I want to express the reason why for a mood event using a photograph.
     *
     * US 02.03.01
     * As a participant, I want to specify the social situation for a mood event to be one of: alone,
     * with one other person, with two to several people, or with a crowd.
     *
     * US 06.01.01
     * As a participant, I want to optionally attach my current location to a mood event.
     */
    @Test
    public void AddNewMood1(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.happyButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        //solo.sleep(5000);
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        //solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "test2");
        //solo.clickOnView(solo.getView(R.id.spinner2));
        solo.pressSpinnerItem(0,4);
        boolean actual = solo.isSpinnerTextSelected(0, "With a Crowd");
        assertTrue("spinner item With a Crowd is not selected", actual);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);

    }

    /**
     * This function tests add new mood event works.
     */
    @Test
    public void AddNewMood2(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.angryButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "test");
        solo.pressSpinnerItem(0,1);
        boolean actual = solo.isSpinnerTextSelected(0, "Alone");
        assertTrue("spinner item Alone is not selected", actual);
        solo.clickOnView(solo.getView(R.id.imageButton4));
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);


    }

    /**
     * This function tests add new mood event works.
     */
    @Test
    public void AddNewMood3(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.scaredButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        solo.clickOnView(solo.getView(R.id.image_import2));//Click image button to choose an image from gallery.
        solo.sleep(6000);
        solo.enterText((EditText) solo.getView(R.id.reason_text3), "test3");
        solo.pressSpinnerItem(0,2);
        boolean actual2 = solo.isSpinnerTextSelected(0, "Along with one person");
        assertTrue("spinner item Along with one person is not selected", actual2);
        solo.clickOnView(solo.getView(R.id.save_button));
        solo.sleep(6000);
        solo.waitForActivity(MainActivity.class, 2000);


    }



    /**
     * This function test if the warning message appears when the user press Next button without
     * Click on one of the emotion icons.
     */

    @Test
    public void NotSelectedEmotion(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForText("Choose an emotion!", 1,2000));
    }


    /**
     * This function test if the user arrives Emotion page when pressing Cancel button.
     */
    @Test
    public void CancelButton(){
        solo.clickOnView(solo.getView(R.id.floatingActionButton));
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
        solo.clickOnView(solo.getView(R.id.sickButton2));
        solo.clickOnView(solo.getView(R.id.save_button3));
        assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.add_cancel)); //click on cancel button.
        solo.waitForActivity(AddMoodFirstActivity.class, 2000);
        solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);

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
