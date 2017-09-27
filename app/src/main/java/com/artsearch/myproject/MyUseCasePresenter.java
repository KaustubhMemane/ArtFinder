package com.artsearch.myproject;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.factory.GettyImageFactory;
import com.artsearch.myproject.view.ui.fragment.PopUpDialogFragment;
import com.artsearch.myproject.view.ui.viewmodel.BaseViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyUseCasePresenter implements MyContract.ToPresenter, MyModel.GetResult {

        private MyContract.PublishToView publishResult;
        private MyModel getImageObj;
        Context mContext;
        GettyImageFactory gettyImageFactory;


    //An object of our mainFragment
    public MyUseCasePresenter(MyContract.PublishToView publishResult, Context context,
                              GettyImageFactory gettyImageFactory) {

        this.publishResult = publishResult;
        this.mContext = context;
        this.gettyImageFactory = gettyImageFactory;

        getImageObj = new MyModel();
        getImageObj.setImageResultListner(this);
    }

    @Override
    public void searchButtonClick(int actionId, String input) {
        if(input.isEmpty() || input == null)
        {
            publishResult.showToastMessage("Enter Artist's Name");
            return;
        }
        getImageObj.SearchImages(actionId, input);
    }

    @Override
    public void onResultBack(List<ImageResult> images, String message, boolean successful) {

        if (successful) {
            List<BaseViewModel> viewModels = new ArrayList<>();
            int i = 0;

            for (ImageResult imageResult : images) {
                viewModels.add(gettyImageFactory.createGettyImageViewModel(i++, imageResult, this::onImageLongPress));
            }

            publishResult.showResult(viewModels);
        }
    }

    @Override
    public void onErrorBack(Throwable error) {
        String errorType;
        if (error instanceof IOException) {
            errorType = "Timeout";
        } else if (error instanceof IllegalStateException) {
            errorType = "ConversionError";
        } else {
            errorType = "Other Error";
        }
        publishResult.showToastMessage(errorType);
    }

    private void onImageLongPress(String id, String uri) {
        //todo - implement new feature
        /*displaying popUpDialog*/
        PopUpDialogFragment.newInstanceOfPopUpDialogFragment(id, uri)
                .show(((FragmentActivity) mContext).getSupportFragmentManager(), "Simple");
    }
}
