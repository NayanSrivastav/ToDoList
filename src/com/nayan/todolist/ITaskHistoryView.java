package com.nayan.todolist;

import java.util.List;

import model.TaskHistory;

public interface ITaskHistoryView {
	void onSuccessFulLoad(List<TaskHistory> taskHistories);

	void onUpdateFail();

	void showHistory();
	
	void updateTask(TaskHistory taskHistory);
	
	void onNoHistoryFound();
	void onSuccessFUlUpdate();
}
