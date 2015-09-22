package com.nayan.todolist;

public interface TaskListFragmentInterface {
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
	public void onSuccess();
}
