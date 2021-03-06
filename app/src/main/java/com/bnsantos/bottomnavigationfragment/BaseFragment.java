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
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
  private BottomNavigationView mNavigationView;

  private FrameLayout mContentLayout;
  private FrameLayout mBottomLayout;
  private BlankFragment mBottomFragment;

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

    mBottomLayout = (FrameLayout) view.findViewById(R.id.bottomContainer);
    mContentLayout = (FrameLayout) view.findViewById(R.id.layout);
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

  public void toggleBottomFragment(){
    if(mBottomLayout.getVisibility() == View.VISIBLE){
      slideToBottom(mBottomLayout);
    }else {
      slideFromBottom(mBottomLayout);
    }
  }

  private void slideToBottom(View view) {
    TranslateAnimation animate = new TranslateAnimation(0, 0, 0, mContentLayout.getHeight());
    animate.setDuration(1000);
    animate.setFillAfter(true);
    view.startAnimation(animate);
    view.setVisibility(View.GONE);

    getChildFragmentManager().beginTransaction()
        .remove(mBottomFragment)
        .commit();
  }

  private void slideFromBottom(View view) {
    mBottomFragment = BlankFragment.newInstance();
    getChildFragmentManager().beginTransaction()
        .add(R.id.bottomContainer, mBottomFragment)
        .commit();

    TranslateAnimation animate = new TranslateAnimation(0, 0, mContentLayout.getHeight(), 0);
    animate.setDuration(1000);
    animate.setFillAfter(true);
    view.startAnimation(animate);
    view.setVisibility(View.VISIBLE);
  }

  public boolean onBackPressed() {
    if(mBottomLayout.getVisibility() == View.VISIBLE){
      slideToBottom(mBottomLayout);
      return false;
    }
    return true;
  }
}
