package com.nayan.todolist;

import java.util.List;

import model.Task;
import android.graphics.drawable.ColorDrawable;

public interface TaskListFragmentInterface {
	public static final ColorDrawable LIST_VIEW_DIVIDER_COLOR_DRAWABLE = new ColorDrawable(
			android.R.color.secondary_text_light);

	/**
	 * load the tasks of respective stages into the list on load of fragment
	 * 
	 * @param stage
	 */
	public void onLoad(String stage);

	/**
	 * specify the event that would take place if task List of respective stage
	 * is empty
	 */
	public void onNoResult();

	/**
	 * specify the event that would take place if task List of respective stage
	 * is non-empty
	 */
	public void onSuccess(List<Task> taskList);
}
