package com.bnsantos.bottomnavigationfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private BaseFragment mFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    mFragment = BaseFragment.newInstance();
    ft.add(R.id.container, mFragment, mFragment.getTag());
    ft.commit();
  }

  @Override
  public void onBackPressed() {
    if(mFragment.onBackPressed())
      super.onBackPressed();
  }
}
