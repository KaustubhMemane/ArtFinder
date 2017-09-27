package com.artsearch.myproject;

import com.artsearch.myproject.view.ui.viewmodel.BaseViewModel;

import java.util.List;


public interface MyContract {


    //Our view handles this methods
    interface PublishToView {
        void showResult(List<BaseViewModel> viewModels);

        void showToastMessage(String message);
    }

    //Passes click event from our view (MainFragment) to the presenter
    interface ToPresenter {
        void searchButtonClick(int actionId, String input);
    }
}
