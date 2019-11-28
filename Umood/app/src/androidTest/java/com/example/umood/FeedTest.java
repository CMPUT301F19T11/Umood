package com.example.umood;

import android.widget.EditText;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class FeedTest{
        private Solo solo;
        String date;
        String time;
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
         * This function it to add a mood event
         * Assumption:
         * user test is following user qianyu
         */
        @Test
        public void checkMostRecentofFollowing(){
                //Asserts that the current activity is the LoginActivity. Otherwise, show "Wrong Activity".
                solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
                //Get view for EditText and enter a username.
                solo.enterText((EditText)solo.getView(R.id.username),"qianyu");
                solo.clickOnView(solo.getView(R.id.cancel2));
                solo.sleep(6000);
                assertTrue(solo.waitForActivity(MainActivity.class, 2000));


                solo.clickOnView(solo.getView(R.id.floatingActionButton));
                solo.assertCurrentActivity("Wrong Activity", AddMoodFirstActivity.class);
                solo.clickOnView(solo.getView(R.id.happyButton2));
                solo.clickOnView(solo.getView(R.id.save_button3));
                //solo.sleep(5000);
                assertTrue(solo.waitForActivity(addMoodInfo.class, 2000));

                solo.sleep(1000);
                //solo.clickOnView(solo.getView(R.id.spinner2));
                solo.pressSpinnerItem(0,4);

                solo.clickOnView(solo.getView(R.id.imageButton4));

                TextView a = (TextView) solo.getCurrentActivity().findViewById(R.id.reason_text2);
                date = a.getText().toString();
                TextView b = (TextView) solo.getCurrentActivity().findViewById(R.id.reason_text2);
                time = b.getText().toString();

                solo.clickOnView(solo.getView(R.id.save_button));
                solo.sleep(1000);

                solo.waitForActivity(MainActivity.class, 2000);
                //Asserts that the current activity is the MainActivity. Otherwise, show "Wrong Activity".
                solo.assertCurrentActivity("Wrong Activity", MainActivity.class);




        }
        @Test
        public void checkFeed() {
                try {
                        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);

                        //Get view for EditText and enter a username.
                        solo.enterText((EditText) solo.getView(R.id.username), "test");
                        solo.clickOnView(solo.getView(R.id.cancel2));
                        solo.sleep(1000);
                        assertTrue(solo.waitForActivity(MainActivity.class, 2000));

                        solo.clickOnText("Profile");

                        solo.clickOnText("Feed");
                        assertTrue(solo.waitForActivity(FeedActivity.class, 2000));
                        assertTrue(solo.waitForText(date));
                        assertTrue(solo.waitForText(time));
                } catch (Throwable e){
                        e.printStackTrace();
                }

        }
}
