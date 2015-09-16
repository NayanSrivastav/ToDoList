package presenter;

import java.util.Map;

import interactor.ITaskCreaterInteractor;
import interactor.TaskCreateInteractorImpl;

import com.nayan.todolist.IHomeView;

public class CreateTaskPresenterImpl implements ICreateTaskPresenter {
	IHomeView homeView;
	ITaskCreaterInteractor taskCreateInteractor;
	Map<String, String> data;

	public CreateTaskPresenterImpl(IHomeView homeView) {
		super();
		this.homeView = homeView;
		taskCreateInteractor = new TaskCreateInteractorImpl();
	}

	@Override
	public void validateData(Map<String, String> data) {
		this.data = data;
		if (data.containsKey("taskName")) {
			if (data.get("taskName").length() > 0) {
				if (data.containsKey("desc") && data.get("desc").length() > 0) {
					taskCreateInteractor.createTask(data);
				}
			}
		}
	}

	@Override
	public void onSuccess() {
	}

	@Override
	public void onFailure() {
	}

	@Override
	public void onDataError() {
	}

}
