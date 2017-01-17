package com.bnsantos.bottomnavigationfragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
  private static final String ARG_TEXT = "param1";
  private static final String ARG_COLOR = "param2";

  private String mText;
  private int mColor;

  public MenuFragment() { }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param text display text.
   * @param color background color.
   * @return A new instance of fragment MenuFragment.
   */
  public static MenuFragment newInstance(String text, int color) {
    MenuFragment fragment = new MenuFragment();
    Bundle args = new Bundle();
    args.putString(ARG_TEXT, text);
    args.putInt(ARG_COLOR, color);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mText = getArguments().getString(ARG_TEXT);
      mColor = getArguments().getInt(ARG_COLOR, Color.BLACK);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_menu, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (savedInstanceState == null) {
      Bundle args = getArguments();
      mText = args.getString(ARG_TEXT);
      mColor = args.getInt(ARG_COLOR);
    } else {
      mText = savedInstanceState.getString(ARG_TEXT);
      mColor = savedInstanceState.getInt(ARG_COLOR);
    }

    TextView textView = (TextView) view.findViewById(R.id.text);
    textView.setText(mText);

    view.setBackgroundColor(mColor);
    view.findViewById(R.id.action).setOnClickListener(this);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putString(ARG_TEXT, mText);
    outState.putInt(ARG_COLOR, mColor);
    super.onSaveInstanceState(outState);
  }

  @Override
  public void onClick(View v) {
    ((BaseFragment)getParentFragment()).toggleBottomFragment();
  }
}
