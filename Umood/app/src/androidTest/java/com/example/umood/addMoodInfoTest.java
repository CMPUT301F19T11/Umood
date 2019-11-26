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

public class addMoodInfoTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<addMoodInfo> rule =
            new ActivityTestRule<>(addMoodInfo.class, true, true);

    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());


    }


    /**
     * Gets the Activity
     *
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

/**
    @Test
    public void checkcancelButton(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.clickOnView(solo.getView(R.id.add_cancel));
        //assertTrue(solo.waitForActivity(MainActivity.class, 2000));
        //Asserts that the current activity is the MainActivity. Otherwise, show "Wrong Activity".
        solo.waitForActivity(MainActivity.class,2000);
        //solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }
*/
/**
    @Test
    public void checkAddMood(){
        /*
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.clickOnView(solo.getView(R.id.happyButton));
        solo.enterText((EditText)solo.getView(R.id.reason_text),"finished HW");
        solo.pressSpinnerItem(0,1);
        assertTrue( solo.isSpinnerTextSelected(1, "Alone"));
        //solo.clickOnView(solo.getView(R.id.imageButton4));
        //solo.clickOnView(solo.getView(R.id.imageButton2));
        solo.clickOnView(solo.getView(R.id.next));
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

         */
/**
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //solo.clickOnView(solo.getView(R.id.spinner2));
        solo.pressSpinnerItem(0,1);
        assertTrue(solo.isSpinnerTextSelected(0, "Alone"));
        
        solo.pressSpinnerItem(0,2);
        assertTrue(solo.isSpinnerTextSelected(1, "Along with one person"));
        solo.pressSpinnerItem(0,3);
        assertTrue(solo.isSpinnerTextSelected(2, "Along with two to several persons"));
        solo.pressSpinnerItem(0,-1);
        assertTrue(solo.isSpinnerTextSelected(3, "With a Crowd"));
*/
    /**
    }
    @Test
    public void NotSelectedEmotion(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        solo.clickOnView(solo.getView(R.id.next));
        assertTrue(solo.waitForText("Choose an emotion!", 1,2000));
    }


*/



    /**
     * Closes the activity after each test
     * @throws Exception
     */

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
