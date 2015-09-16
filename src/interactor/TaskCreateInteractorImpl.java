package interactor;

import java.util.Map;

import model.Task;

public class TaskCreateInteractorImpl implements ITaskCreaterInteractor {

	@Override
	public void createTask(Map<String, String> data) {
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
		
	}
}
