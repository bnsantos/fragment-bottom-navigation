package com.bnsantos.bottomnavigationfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
  private BottomNavigationView mNavigationView;

  public BaseFragment() { }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment BaseFragment.
   */
  public static BaseFragment newInstance() {
    BaseFragment fragment = new BaseFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_base, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
    mNavigationView.setOnNavigationItemSelectedListener(this);
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    Fragment fragment = null;
    switch (item.getItemId()){
      case R.id.action_left:
        fragment = MenuFragment.newInstance(getString(R.string.left_fragment), getResources().getColor(R.color.colorRight));
        break;
      case R.id.action_right:
        fragment = MenuFragment.newInstance(getString(R.string.right_fragment), getResources().getColor(R.color.colorLeft));
        break;
    }

    if(fragment != null) {
      FragmentTransaction ft = getChildFragmentManager().beginTransaction();
      ft.replace(R.id.container, fragment, fragment.getTag());
      ft.commit();
    }
    return true;
  }
}
