package com.nayan.todolist;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setDisplayUseLogoEnabled(false);
//		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayShowHomeEnabled(false);
//		LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT,
//				LayoutParams.MATCH_PARENT);
//		View customNav = LayoutInflater.from(this).inflate(
//				R.layout.custom_actionbar_view, null); // layout which contains
//														// your
//														// button.
//		actionBar.setCustomView(customNav, lp1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
