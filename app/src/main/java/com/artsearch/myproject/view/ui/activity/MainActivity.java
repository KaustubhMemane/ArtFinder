package com.artsearch.myproject.view.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artsearch.myproject.App;
import com.artsearch.myproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);

    }
}
