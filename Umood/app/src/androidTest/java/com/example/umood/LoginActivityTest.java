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
        solo.clickOnButton("Sign in");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
    }


/*
    @Test
    public void checkSignUp(){
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
        solo.clickOnButton("Sign up");
        solo.assertCurrentActivity("Wrong Activity",SignUpActivity.class);
    }

 */

}
