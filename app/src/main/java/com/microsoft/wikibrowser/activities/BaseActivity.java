package com.microsoft.wikibrowser.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import com.microsoft.wikibrowser.providers.BusProvider;

/**
 * Created by rupam.ghosh on 08/01/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    BusProvider.getInstance().register(this);
  }

  @Override
  protected void onDestroy() {
    ButterKnife.unbind(this);
    BusProvider.getInstance().unregister(this);
    super.onDestroy();
  }
}