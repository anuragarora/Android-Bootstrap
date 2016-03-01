package com.anurag.androidbootstrap.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.anurag.androidbootstrap.R;

/**
 * Created by anurag on 02/03/16.
 */
public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_home);
    }
}
