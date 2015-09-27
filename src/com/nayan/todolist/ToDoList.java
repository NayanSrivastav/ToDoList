package com.nayan.todolist;

import presenter.GetTaskPresenter;
import presenter.IGetTaskPresenter;
import adapter.TaskStageFragmentAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.app.ActionBar;

@SuppressWarnings("deprecation")
public class ToDoList extends BaseActivity implements ActionBar.TabListener , IHomeView{
	private ViewPager viewPager;
	private TaskStageFragmentAdapter pagerAdapter;
	private ActionBar actionBar;
	// tab titles
	private String[] tabs = { "Current Tasks", "Archive", "Planned" };
	IGetTaskPresenter getTaskPresenter;
	public IGetTaskPresenter getGetTaskPresenter() {
		return getTaskPresenter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_test_tab);
		super.setContext(this);
		viewPager = (ViewPager) findViewById(R.id.view_pager_test);
		actionBar = getActionBar();
		pagerAdapter = new TaskStageFragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab()
					.setText(Html.fromHtml(tab_name)).setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		getTaskPresenter=new GetTaskPresenter(this);
	}

	/**
	 * brings swiped fragment to the front
	 */
	@Override
	public void onTabSelected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
	}

	@Override
	public void openTask() {
	}

	@Override
	public void onSuccess() {
	}

	@Override
	public void onFailure() {
	}

}
