package com.artsearch.myproject;

import android.content.Context;

import com.artsearch.myproject.factory.GettyImageFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MyUseCasePresenterTest {

    @Mock
    private MyContract.PublishToView publishResult;

    @Mock
    private MyModel getImageObj;

    @Mock
    Context mContext;

    @Mock
    GettyImageFactory gettyImageFactory;

    @Mock
    MyModel myModel;

    @Mock
    MyUseCasePresenter myUseCasePresenter;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void searchButtonClick() throws Exception {
        int actionId = 100;
        String input = null;
        myModel.SearchImages(actionId,input);
    }

    @Test
    public void onResultBack() throws Exception {

    }

    @Test
    public void onErrorBack() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

}