package com.example.umood;

import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class FollowingTest{
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
         * This function test haha
         */
        @Test
        public void checkMostRecentofFollowing(){
                //Asserts that the current activity is the LoginActivity. Otherwise, show "Wrong Activity".
                solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
                //Get view for EditText and enter a username.
                solo.enterText((EditText)solo.getView(R.id.username),"qianyu");
                solo.clickOnView(solo.getView(R.id.cancel));
                solo.sleep(6000);
                assertTrue(solo.waitForActivity(MainActivity.class, 2000));
                //Asserts that the current activity is the MainActivity. Otherwise, show "Wrong Activity".
                solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        }
}
