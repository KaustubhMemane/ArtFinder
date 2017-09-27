package com.artsearch.myproject;

import com.artsearch.myproject.api.GettyImageService;
import com.artsearch.myproject.api.model.ImageResponse;
import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.api.model.Metadata;
import com.artsearch.myproject.api.model.MetadataResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MyPopUpModel {

    @Inject
    GettyImageService gettyImageService;

    public GetMetaResult getMetaResult;

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public interface GetMetaResult {
        void onMetaResultBack(List<Metadata> imagesMetaData, String message, boolean successful);

        void onSimilarImagesBack(List<ImageResult> similarImages, String message, boolean success);

        void onErrorConditionBack(Throwable error, String Message);
    }

    public void setImageMetaResultListner(GetMetaResult getMetaResult) {
        this.getMetaResult = getMetaResult;
    }


    @Inject
    public MyPopUpModel() {
        App.getInstance().getAppComponent().inject(this);
    }

    public void SearchMetaData(String id, String uri) {
        Observable<MetadataResponse> callMetaData = gettyImageService.getImageMetadata(id);
        subScribeForMetadata(callMetaData);

        Observable<ImageResponse> callSimilarImages = gettyImageService.getSimilarImages(id);
        subScribeForSimilarImages(callSimilarImages);
    }

    private void subScribeForMetadata(Observable<MetadataResponse> call) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<MetadataResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MetadataResponse metadataResponse) {
                        getMetaResult.onMetaResultBack(metadataResponse.getMetadata(),
                                "success", true);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        compositeSubscription.unsubscribe();
                        getMetaResult.onErrorConditionBack(error, "Information");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void subScribeForSimilarImages(Observable<ImageResponse> call) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ImageResponse imageResponse) {
                        getMetaResult.onSimilarImagesBack(imageResponse.getImages(),
                                "success", true);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        //todo - show error
                        getMetaResult.onErrorConditionBack(error, "SimilarImages:");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
