package com.artsearch.myproject.api;

import android.content.Context;

import com.artsearch.myproject.R;
import com.artsearch.myproject.injection.ForApplication;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class GettyImageInterceptor implements Interceptor {

    @Inject
    @ForApplication
    Context context;


    @Inject
    public GettyImageInterceptor() {
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        String apiKey = context.getString(R.string.getty_api_key);

        Request original = chain.request();
        Request request = original
                .newBuilder()

                .header("Api-Key", apiKey)
                .build();

        return chain.proceed(request);
    }
}
