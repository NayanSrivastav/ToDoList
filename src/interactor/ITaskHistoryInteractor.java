package interactor;


import android.content.Context;
import model.Task;
import model.TaskHistory;

public interface ITaskHistoryInteractor {
	public void updateTask(Task task, TaskHistory taskHistory, Context context);
	public void loadTaskHistory(int id, Context context);
}
