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
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public void openTask() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSuccess() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onFailure() {
		// TODO Auto-generated method stub
		
	}

}
