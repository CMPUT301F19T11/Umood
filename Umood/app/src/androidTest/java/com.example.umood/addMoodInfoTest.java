package com.example.umood;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.umood.ui.home.HomeFragment;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


/**
 * Test class for addMoodInfo. All the UI tests are written here. Robotium test framework is
 used
 */
public class addMoodInfoTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<addMoodInfo> rule =
            new ActivityTestRule<>(addMoodInfo.class,true,true);
    @Before
    public void setUp(){
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());

    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();

    }

    @Test
    public void checkHappy(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //check no emoticon select, stay at the current activity
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //select happy
        solo.clickOnImageButton(R.drawable.happy);
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity",HomeFragment.class);


    }

    @Test
    public void checkConfused(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //check no emoticon select, stay at the current activity
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //select confused
        solo.clickOnImageButton(R.drawable.confused);
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity",HomeFragment.class);

    }

    @Test
    public void checkBoring(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //check no emoticon select, stay at the current activity
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //select boring
        solo.clickOnImageButton(R.drawable.bored);
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity",HomeFragment.class);

    }
    @Test
    public void checkCry(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //check no emoticon select, stay at the current activity
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //select crying
        solo.clickOnImageButton(R.drawable.crying);
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity",HomeFragment.class);

    }
    @Test
    public void checkSad(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //check no emoticon select, stay at the current activity
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //select sad
        solo.clickOnImageButton(R.drawable.sad);
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity",HomeFragment.class);

    }
    @Test
    public void checkLove(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //check no emoticon select, stay at the current activity
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);

        //select love
        solo.clickOnImageButton(R.drawable.inlove);
        solo.clickOnButton("NEXT");
        solo.assertCurrentActivity("Wrong Activity",HomeFragment.class);
    }

    @Test
    public void cancel(){
        solo.assertCurrentActivity("Wrong Activity", addMoodInfo.class);
        //check cancel, back to home page
        solo.clickOnButton("CANCEL");
        solo.assertCurrentActivity("Wrong Activity", HomeFragment.class);
    }




    @After
    public void tearDown(){
        solo.finishOpenedActivities();
    }


}
