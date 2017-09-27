package com.artsearch.myproject;


import com.artsearch.myproject.api.GettyImageService;
import com.artsearch.myproject.api.model.ImageResponse;
import com.artsearch.myproject.api.model.ImageResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MyModel {

    @Inject
    GettyImageService gettyImageService;

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public GetResult getResult;

    public interface GetResult {
        void onResultBack(List<ImageResult> images, String message, boolean successful);

        void onErrorBack(Throwable error);
    }

    public void setImageResultListner(GetResult getResult) {
        this.getResult = getResult;
    }

    @Inject
    public MyModel() {
        App.getInstance().getAppComponent().inject(this);
    }

    public void SearchImages(int actionId, String input) {

        Observable<ImageResponse> call = gettyImageService.searchImages(input);
        subScribeForData(call);

    }

    private void subScribeForData(Observable<ImageResponse> call) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull ImageResponse imageResponse) {
                        //updateImages(imageResponse.getImages());
                        getResult.onResultBack(imageResponse.getImages(),
                                "success", true);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        //progressBar.setVisibility(GONE);
                        //todo - show error
                        compositeSubscription.unsubscribe();
                        getResult.onErrorBack(error);
                    }

                    @Override
                    public void onComplete() {
                        //progressBar.setVisibility(GONE);
                    }
                });
    }


}
