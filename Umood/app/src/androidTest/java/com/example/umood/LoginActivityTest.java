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

/**
 * 01.01.01
 * 01.02.01
 * 01.03.01
 * 01.05.01
 * 02.01.01
 * 02.02.01
 * 02.03.01
 * 03.01.01
 * 04.01.01
 * 05.01.01
 * 05.02.01
 * 05.03.01
 * 06.01.01 (new)
 * 06.02.01 (new)
 * 06.03.01 (new)
 * 01.04.01 (new)
 * 04.02.01 (new)
 */

public class LoginActivityTest {
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
    }

    /**
     * This function test if the user arrives MainActivity when he/she enters valid username
     * and press the SIGN IN button.
     */

    @Test
    public void checkLoginActivity(){
        //Asserts that the current activity is the LoginActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        //Get view for EditText and enter a username.
        solo.enterText((EditText)solo.getView(R.id.username),"yifan");
        solo.clickOnView(solo.getView(R.id.cancel));
        solo.sleep(6000);
        assertTrue(solo.waitForActivity(MainActivity.class, 2000));
        //Asserts that the current activity is the MainActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }

    /**
     * This function test if the user arrives SignUpActivity when he/she press the Sign up button.
     */
    @Test
    public void  checkSignupActivity(){
        //Asserts that the current activity is the LoginActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.clickOnView(solo.getView(R.id.signup));
        //solo.clickOnButton("Not Registered?Sign Up here");
        solo.sleep(2000);
        assertTrue(solo.waitForActivity(SignUpActivity.class, 2000));
        //Asserts that the current activity is the SignUpActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
    }

    /**
     * This function test if the warning message appears when the user press SIGN IN button without
     * inputting a username.
     */
    @Test
    public void emptyUsernameLogin(){
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.clickOnView(solo.getView(R.id.cancel));
        //solo.clickOnButton("SIGN IN");
        assertTrue(solo.waitForText("The username cannot be empty", 1,2000));
    }


    /**
     * This function test if the warning message appears when the user inputs a username that
     * does not exist.
     *
     */
    @Test
    public void NotExistUserLogin(){
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.username),"NotExistedUsername");
        solo.clickOnView(solo.getView(R.id.cancel));
        //solo.clickOnButton("SIGN IN");
        assertTrue(solo.waitForText("The username does not exist", 1,2000));

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
