package com.bnsantos.bottomnavigationfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankFragment extends Fragment {
  public BlankFragment() { }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   * @return A new instance of fragment BlankFragment.
   */
  public static BlankFragment newInstance() {
    BlankFragment fragment = new BlankFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_blank, container, false);
  }
}
