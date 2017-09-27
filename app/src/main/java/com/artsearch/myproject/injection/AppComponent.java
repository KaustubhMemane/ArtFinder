package com.artsearch.myproject.injection;

import com.artsearch.myproject.App;
import com.artsearch.myproject.MyModel;
import com.artsearch.myproject.MyPopUpModel;
import com.artsearch.myproject.view.ui.activity.MainActivity;
import com.artsearch.myproject.view.ui.fragment.MainFragment;
import com.artsearch.myproject.view.ui.fragment.PopUpDialogFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(MainActivity activity);

    void inject(MainFragment fragment);

    void inject(PopUpDialogFragment fragment);

    void inject(MyModel getImages_model);

    void inject(MyPopUpModel stashPopUpModel);
}