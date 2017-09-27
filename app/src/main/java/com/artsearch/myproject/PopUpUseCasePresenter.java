package com.artsearch.myproject;

import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.api.model.Metadata;

import java.io.IOException;
import java.util.List;


public class PopUpUseCasePresenter implements MyPopUpContract.popUpToPresenter, MyPopUpModel.GetMetaResult {

    private MyPopUpContract.PublishToPopUpView publishPopUpResult;
    private MyPopUpModel getImageModel;

    public PopUpUseCasePresenter(MyPopUpContract.PublishToPopUpView publishPopUpResult) {
        this.publishPopUpResult = publishPopUpResult;
        getImageModel = new MyPopUpModel();
        getImageModel.setImageMetaResultListner(this);
    }

    @Override
    public void getDataPopUpDialog(String id, String url) {
        getImageModel.SearchMetaData(id, url);
    }

    @Override
    public void onMetaResultBack(List<Metadata> imagesMetaData, String message, boolean successful) {

        if (!imagesMetaData.isEmpty() && successful) {
            publishPopUpResult.showMetadataResult(imagesMetaData);
            publishPopUpResult.showToastMessage(message);
        }
    }

    @Override
    public void onSimilarImagesBack(List<ImageResult> similarImages, String message, boolean successful) {
        if (similarImages.size() > 3 && successful) {
            publishPopUpResult.showSimilarImagesResult(similarImages.subList(0, 3));
            publishPopUpResult.showToastMessage(message);
        }
    }

    @Override
    public void onErrorConditionBack(Throwable error, String message) {
        String errorType;
        if (error instanceof IOException) {
            errorType = "Timeout";
        } else if (error instanceof IllegalStateException) {
            errorType = "ConversionError";
        } else {
            errorType = "Other Error";
        }
        publishPopUpResult.showToastMessage(message + " " + errorType);
    }
}
