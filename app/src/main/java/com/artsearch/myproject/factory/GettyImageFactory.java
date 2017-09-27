package com.artsearch.myproject.factory;

import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.view.ui.viewmodel.GettyImageViewModel;

import javax.inject.Inject;

public class GettyImageFactory {

    @Inject
    public GettyImageFactory() {
    }

    public GettyImageViewModel createGettyImageViewModel(int id, ImageResult imageResult, GettyImageViewModel.Listener listener) {
        return new GettyImageViewModel(id, imageResult, listener);
    }
}
