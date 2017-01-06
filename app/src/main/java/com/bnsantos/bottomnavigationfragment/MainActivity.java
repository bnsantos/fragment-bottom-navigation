package com.bnsantos.bottomnavigationfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    BaseFragment fragment = BaseFragment.newInstance();
    ft.add(R.id.container, fragment, fragment.getTag());
    ft.commit();
  }
}
