package presenter;

import java.util.List;

import model.Task;
import model.TaskHistory;

public interface ITaskHistoryPressenter {
	public void updatetask(Task task, TaskHistory taskHistory);
	public void onSuccess();
	public void onFailure();
	public void loadHistory(int id);
	public void onNoHistoryFound();
	public void onSuccessFulLoad(List<TaskHistory> taskHistories);
}
