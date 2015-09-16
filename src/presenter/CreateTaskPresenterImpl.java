package presenter;

import java.util.Map;

import interactor.ITaskCreaterInteractor;
import interactor.TaskCreateInteractorImpl;
import android.app.Activity;

import com.nayan.todolist.IHomeView;

public class CreateTaskPresenterImpl implements ICreateTaskPresenter {
	IHomeView homeView;
	ITaskCreaterInteractor taskCreateInteractor;
	Map<String, String> data;

	public CreateTaskPresenterImpl(IHomeView homeView) {
		super();
		this.homeView = homeView;
		taskCreateInteractor = new TaskCreateInteractorImpl(this);
	}

	@Override
	public void validateData(Map<String, String> data) {
		this.data = data;
		if (data.containsKey("taskName")) {
			if (data.get("taskName").length() > 0) {
				if (data.containsKey("desc") && data.get("desc").length() > 0) {
					taskCreateInteractor.createTask(data, (Activity)homeView);
				}
			}
		}
	}

	@Override
	public void onSuccess() {
		homeView.onSuccess();
	}

	@Override
	public void onFailure() {
		homeView.onFailure();
	}

	@Override
	public void onDataError() {
		homeView.onFailure();
	}

}
