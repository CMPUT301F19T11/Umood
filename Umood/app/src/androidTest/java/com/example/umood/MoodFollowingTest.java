package com.example.umood;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class MoodFollowingTest {
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
        solo.clickOnView(solo.getView(R.id.cancel2));
        solo.sleep(3000);
    }



    /**
     * US 05.01.01
     * As a participant, I want to ask another participation to follow their most recent mood event.
     */

    @Test
    public void testSendFollowingRequest(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.addFollowing));
        solo.sleep(3000);
        solo.waitForActivity(AddFollowingActivity.class, 2000);
        solo.enterText((EditText) solo.getView(R.id.add_following), "NewTest");
        solo.clickOnView(solo.getView(R.id.confirm));
        assertTrue(solo.waitForText("Following request has been sent!!", 1,2000));

    }

    /**
     * US 05.01.01
     * As a participant, I want to grant another participation permission to follow my most recent mood event.
     * Assumption:
     * If logged in as yifan, there exists a username (test) in following request.
     *
     */
    @Test
    public void testReceiveFollowingRequest(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.request));
        solo.sleep(3000);
        solo.waitForActivity(FollowingRequest.class, 2000);
        ListView listView = (ListView) solo.getView(R.id.friend_request_list);
        assertTrue(listView.getAdapter().getCount() > 0);

    }

    /**
     *
     * This function tests that feed button works. user could view following mood events.
     */
    @Test
    public void testFeedButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.button_feed));
        solo.sleep(3000);
        solo.waitForActivity(FeedActivity.class, 2000);

    }


    /**
     * This function test if the warning message appears when the user inputs a not exist username.
     */
    @Test
    public void testNotExistUser(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.addFollowing));
        solo.sleep(3000);
        solo.waitForActivity(AddFollowingActivity.class, 2000);
        solo.enterText((EditText) solo.getView(R.id.add_following), "yifan30");
        solo.clickOnView(solo.getView(R.id.confirm));
        assertTrue(solo.waitForText("The username does not exist!!", 1,2000));

    }

    /**
     * This function test if the warning message appears when the user clicks confirm button without input username.
     */

    @Test
    public void testEmptyUser(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.addFollowing));
        solo.sleep(3000);
        solo.waitForActivity(AddFollowingActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.confirm));
        assertTrue(solo.waitForText("The username Cannot Be Empty!!", 1,2000));

    }

    /**
     * This functions test if the user go back to the profile page when clicking on CANCEL button.
     */

    @Test
    public void TestCancelButton(){
        solo.clickOnView(solo.getView(R.id.nav_view).findViewById(R.id.navigation_notifications));
        solo.waitForFragmentById(R.id.navigation_notifications);
        solo.clickOnView(solo.getView(R.id.addFollowing));
        solo.sleep(5000);
        solo.waitForActivity(AddFollowingActivity.class, 2000);
        solo.clickOnView(solo.getView(R.id.cancel2));
        solo.waitForFragmentById(R.id.navigation_notifications);

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
