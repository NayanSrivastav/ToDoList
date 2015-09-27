package com.nayan.todolist;

import java.util.List;

import presenter.IGetTaskPresenter;
import model.Task;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncGetTasksByStage extends AsyncTask<Void, Void, Void> {
	TaskListFragmentInterface taskListFragmentInterface;
	Context context;
	List<Task> taskList;
	String stage;
	IGetTaskPresenter getTaskPresenter;

	public AsyncGetTasksByStage(String stage,
			IGetTaskPresenter getTaskPresenter,
			TaskListFragmentInterface taskListFragmentInterface) {
		super();
		this.stage = stage;
		this.getTaskPresenter = getTaskPresenter;
		this.taskListFragmentInterface = taskListFragmentInterface;
	}

	@Override
	protected Void doInBackground(Void... params) {
		taskList = getTaskPresenter.gettaskList(stage);
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (taskList == null) {
			taskListFragmentInterface.onNoResult();
		} else {
			taskListFragmentInterface.onSuccess(taskList);
		}
	}
}
