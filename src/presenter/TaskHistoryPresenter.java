package presenter;

import java.util.List;

import interactor.ITaskHistoryInteractor;
import interactor.TaskHistoryInteractorImpl;
import android.content.Context;

import com.nayan.todolist.ITaskHistoryView;

import model.Task;
import model.TaskHistory;

public class TaskHistoryPresenter implements ITaskHistoryPressenter {
	ITaskHistoryView iTaskHistoryView;
	ITaskHistoryInteractor iTaskHistoryInteractor;

	public TaskHistoryPresenter(ITaskHistoryView iTaskHistoryView) {
		super();
		this.iTaskHistoryView = iTaskHistoryView;
		this.iTaskHistoryInteractor = new TaskHistoryInteractorImpl(this);
	}

	@Override
	public void updatetask(Task task, TaskHistory taskHistory) {
		iTaskHistoryInteractor.updateTask(task, taskHistory,
				(Context) iTaskHistoryView);
	}

	@Override
	public void onSuccess() {
		iTaskHistoryView.onSuccessFUlUpdate();
	}

	@Override
	public void onFailure() {
		iTaskHistoryView.onUpdateFail();
	}

	@Override
	public void loadHistory(int id) {
		iTaskHistoryInteractor
				.loadTaskHistory(id, (Context) iTaskHistoryView);
	}

	@Override
	public void onNoHistoryFound() {
		iTaskHistoryView.onNoHistoryFound();
	}

	@Override
	public void onSuccessFulLoad(List<TaskHistory> taskHistories) {
		iTaskHistoryView.onSuccessFulLoad(taskHistories);
	}

}
