package com.nayan.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class HomeActivity extends BaseActivity implements IHomeView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		super.setContext(this);
		Intent intent=new Intent(this, ToDoList.class);
		startActivity(intent);
	}

	@Override
	public void openTask() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
