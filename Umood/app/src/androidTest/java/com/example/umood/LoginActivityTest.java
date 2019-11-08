package com.example.umood;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.umood.ui.home.HomeFragment;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LoginActivityTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class, true, true);

    @Before
    public void setUp() {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());

    }

    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();

    }
    /**
     *check for login activity
     * enter an exist username and click login
     */
    @Test
    public void checkLogin(){
        //do not enter username
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
        solo.clickOnButton("Sign in");
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);

        //enter an exist username
        solo.enterText((EditText) solo.getView(R.id.username),"test");
        assertTrue(solo.waitForText("test",1,10000));
        solo.clickOnButton("Sign in");
        solo.clearEditText((EditText) solo.getView(R.id.username));

        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
    }

    @Test
    public void checkHappy() {
        solo.enterText((EditText) solo.getView(R.id.username),"test");
        assertTrue(solo.waitForText("test",1,10000));
        solo.clickOnButton("Sign in");


        assertTrue(solo.waitForActivity(MainActivity.class,2000));

        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnImageButton(0);
        assertTrue(solo.waitForActivity(addMoodInfo.class,2000));

        solo.assertCurrentActivity("Wrong Activity",addMoodInfo.class);


        solo.clickOnImageButton(0);
        solo.enterText((EditText)solo.getView(R.id.reason_text),"no reason");
        //assertTrue(solo.waitForText("no reason",1,10000));

        assertTrue(solo.waitForText("no reason",1,2000));
        solo.clickOnButton("Finish");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        assertEquals(solo.getCurrentActivity().getClass(),MainActivity.class);
        //solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }
    @Test
    public void checkCry() {
        solo.enterText((EditText) solo.getView(R.id.username),"test");
        assertTrue(solo.waitForText("test",1,10000));
        solo.clickOnButton("Sign in");


        assertTrue(solo.waitForActivity(MainActivity.class,2000));

        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnImageButton(0);
        assertTrue(solo.waitForActivity(addMoodInfo.class,2000));

        solo.assertCurrentActivity("Wrong Activity",addMoodInfo.class);


        solo.clickOnImageButton(1);
        solo.enterText((EditText)solo.getView(R.id.reason_text),"no reason");
        //assertTrue(solo.waitForText("no reason",1,10000));

        assertTrue(solo.waitForText("no reason",1,2000));
        solo.clickOnButton("Finish");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        assertEquals(solo.getCurrentActivity().getClass(),MainActivity.class);
        //solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }
    @Test
    public void checkAngry() {
        solo.enterText((EditText) solo.getView(R.id.username),"test");
        assertTrue(solo.waitForText("test",1,10000));
        solo.clickOnButton("Sign in");


        assertTrue(solo.waitForActivity(MainActivity.class,2000));

        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnImageButton(0);
        assertTrue(solo.waitForActivity(addMoodInfo.class,2000));

        solo.assertCurrentActivity("Wrong Activity",addMoodInfo.class);


        solo.clickOnImageButton(2);
        solo.enterText((EditText)solo.getView(R.id.reason_text),"no reason");
        //assertTrue(solo.waitForText("no reason",1,10000));

        assertTrue(solo.waitForText("no reason",1,2000));
        solo.clickOnButton("Finish");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        assertEquals(solo.getCurrentActivity().getClass(),MainActivity.class);
        //solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }
    @Test
    public void checkSad() {
        solo.enterText((EditText) solo.getView(R.id.username),"test");
        assertTrue(solo.waitForText("test",1,10000));
        solo.clickOnButton("Sign in");


        assertTrue(solo.waitForActivity(MainActivity.class,2000));

        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnImageButton(0);
        assertTrue(solo.waitForActivity(addMoodInfo.class,2000));

        solo.assertCurrentActivity("Wrong Activity",addMoodInfo.class);


        solo.clickOnImageButton(3);
        solo.enterText((EditText)solo.getView(R.id.reason_text),"no reason");
        //assertTrue(solo.waitForText("no reason",1,10000));

        assertTrue(solo.waitForText("no reason",1,2000));
        solo.clickOnButton("Finish");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        assertEquals(solo.getCurrentActivity().getClass(),MainActivity.class);
        //solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }

    @Test
    public void checkMoodHistory() {
        solo.enterText((EditText) solo.getView(R.id.username),"test");
        assertTrue(solo.waitForText("test",1,10000));
        solo.clickOnButton("Sign in");


        assertTrue(solo.waitForActivity(MainActivity.class,2000));

        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnImageButton(0);
        assertTrue(solo.waitForActivity(addMoodInfo.class,2000));

        solo.assertCurrentActivity("Wrong Activity",addMoodInfo.class);


        solo.clickOnImageButton(3);
        solo.enterText((EditText)solo.getView(R.id.reason_text),"no reason");
        //assertTrue(solo.waitForText("no reason",1,10000));

        assertTrue(solo.waitForText("no reason",1,2000));
        solo.clickOnButton("Finish");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        assertEquals(solo.getCurrentActivity().getClass(),MainActivity.class);


        solo.clickOnText("Profile");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        solo.clickOnText("History");
        assertTrue(solo.waitForActivity(MoodHistory.class,2000));

        solo.clickOnImage(0);
        assertTrue(solo.waitForActivity(DetailMoodActivity.class,2000));
        assertEquals(solo.getCurrentActivity().getClass(),DetailMoodActivity.class);
        solo.clickOnText("Delete");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);


        solo.clickOnText("Profile");
        assertTrue(solo.waitForActivity(MainActivity.class,2000));
        assertFalse(solo.searchText("99"));





        //solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }




}
