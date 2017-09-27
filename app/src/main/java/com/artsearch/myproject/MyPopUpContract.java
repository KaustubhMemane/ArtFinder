package com.artsearch.myproject;

import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.api.model.Metadata;

import java.util.List;


public interface MyPopUpContract {


    interface PublishToPopUpView {
        void showSimilarImagesResult(List<ImageResult> similarImage);

        void showMetadataResult(List<Metadata> metadatas);

        void showToastMessage(String message);
    }

    interface popUpToPresenter {
        void getDataPopUpDialog(String id, String url);
    }
}
