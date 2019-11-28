package com.example.umood;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;

public class SignUpActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<SignUpActivity> rule =
            new ActivityTestRule<>(SignUpActivity.class, true, true);
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
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }


    /**
     * This function test if the warning message appears when the user press SIGN IN button without
     * inputting a username.
     */
    @Test
    public void emptyUsernameSignup(){
        //Asserts that the current activity is the SignUpActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.clickOnView(solo.getView(R.id.signup1));
        assertTrue(solo.waitForText("Username Cannot be empty!", 1,2000));

    }


    /**
     * This function test if the warning message appears when the user inputs an exist username.
     *
     */
    @Test
    public void ExistUserSignUp(){
        //Asserts that the current activity is the SignUpActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        // The user test should always be exist.
        solo.enterText((EditText)solo.getView(R.id.username),"test");
        solo.clickOnView(solo.getView(R.id.signup1));
        assertTrue(solo.waitForText("The Username has been taken!", 1, 2000));
    }

    @Test
    public void  checkSignupActivity(){
        //Asserts that the current activity is the SignUpActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.clickOnView(solo.getView(R.id.signup_cancel));
        solo.sleep(2000);
        assertTrue(solo.waitForActivity(LoginActivity.class, 2000));
        //Asserts that the current activity is the LoginActivity. Otherwise, show "Wrong Activity".
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
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
