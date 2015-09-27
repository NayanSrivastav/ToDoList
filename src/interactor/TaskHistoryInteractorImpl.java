package interactor;

import java.util.List;

import presenter.ITaskHistoryPressenter;
import database.DatabaseHandler;
import android.content.Context;
import android.os.AsyncTask;
import model.Task;
import model.TaskHistory;

public class TaskHistoryInteractorImpl implements ITaskHistoryInteractor {
	private ITaskHistoryPressenter iTaskHistoryPressenter;

	public TaskHistoryInteractorImpl(
			ITaskHistoryPressenter iTaskHistoryPressenter) {
		super();
		this.iTaskHistoryPressenter = iTaskHistoryPressenter;
	}

	@Override
	public void updateTask(Task task, TaskHistory taskHistory, Context context) {
		new AsyncTaskModify(task, taskHistory, context).execute();
	}

	@Override
	public void loadTaskHistory(int id, Context context) {
		DatabaseHandler dbHandler = new DatabaseHandler(context);

		List<TaskHistory> list= dbHandler.getHistoryOfTask(id);
		if(list==null)
		{
			iTaskHistoryPressenter.onNoHistoryFound();
		}
		else
		{
			iTaskHistoryPressenter.onSuccessFulLoad(list);
		}
	}

	private class AsyncTaskModify extends AsyncTask<Void, Void, Void> {
		private Task task;
		private TaskHistory taskHistory;
		private DatabaseHandler dbHandler;
		private boolean updateResult = false;
		public AsyncTaskModify(Task task, TaskHistory taskHistory,
				Context context) {
			this.task = task;
			dbHandler = new DatabaseHandler(context);
			this.taskHistory = taskHistory;

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			updateResult = dbHandler.updateTask(task, taskHistory);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (updateResult) {
				iTaskHistoryPressenter.onSuccess();
			} else {
				iTaskHistoryPressenter.onFailure();
			}
		}
	}

}
