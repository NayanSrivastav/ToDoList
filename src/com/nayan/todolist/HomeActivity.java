package com.nayan.todolist;

import android.os.Bundle;
import android.view.Menu;

public class HomeActivity extends BaseActivity implements IHomeView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_home);
		// ActionBar actionBar = getActionBar();
		// actionBar.setCustomView(R.layout.custom_actionbar_view);
	}

	@Override
	public void openTask() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
}
