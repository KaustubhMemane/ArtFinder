package com.artsearch.myproject.view.ui.fragment;


import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.FrameLayout;

import com.artsearch.myproject.R;
import com.artsearch.myproject.view.ui.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by kmemane on 9/10/2017.
 */


/*testing a fragment launch*/
public class MainFragmentTestAndroid {

    @Rule
    public  ActivityTestRule<MainActivity> mainActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityTestRule.getActivity();
    }


    @Test
    public void testInitiate()
    {
        // checking/testing if fragment is initiated or not

        FrameLayout fragmentContainer_MainFragment = (FrameLayout) mActivity.findViewById(R.id.fragment_container);

        assertNotNull(fragmentContainer_MainFragment);

        MainFragment  mainFragment = MainFragment.newInstance();

        mActivity.getSupportFragmentManager().beginTransaction()
                .add(fragmentContainer_MainFragment.getId()
                        ,mainFragment).commitAllowingStateLoss();


        InstrumentationRegistry.getInstrumentation().waitForIdleSync();


        View view = mainFragment.getView().findViewById(R.id.view_in_fragment);

        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {

        mActivity = null;
    }

}