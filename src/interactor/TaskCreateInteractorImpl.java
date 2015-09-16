package interactor;

import java.util.Map;

import presenter.ICreateTaskPresenter;
import android.content.Context;
import database.DatabaseHandler;
import model.Task;

public class TaskCreateInteractorImpl implements ITaskCreaterInteractor {
	
	ICreateTaskPresenter createTaskPresenter;
	public TaskCreateInteractorImpl(ICreateTaskPresenter createTaskPresenter) {
		super();
		this.createTaskPresenter = createTaskPresenter;
	}
	@Override
	public void createTask(Map<String, String> data, Context context) {
		Task task = new Task(data.get("taskName"), data.get("desc"));
		if (data.containsKey("EstDate") && data.get("EstDate") != null
				&& data.get("EstDate").length() > 0) {
			task.setDeadline(data.get("EstDate"));
		}
		if (data.containsKey("selected stage")
				&& data.get("selected stage") != null
				&& data.get("selected stage").length() > 0) {
			task.setCurrentStage(data.get("selected stage"));
		}
		DatabaseHandler dbHandler = new DatabaseHandler(context);
		boolean result=dbHandler.addTask(task);
		if(result)
		{
			createTaskPresenter.onSuccess();
		}
		else
		{
			createTaskPresenter.onFailure();
		}
	}
}
