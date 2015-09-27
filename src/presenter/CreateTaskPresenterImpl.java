package presenter;

import java.util.Map;

import interactor.ITaskCreaterInteractor;
import interactor.TaskCreateInteractorImpl;
import android.app.Activity;

import com.nayan.todolist.IHomeView;
import com.nayan.todolist.TaskActivity;

public class CreateTaskPresenterImpl implements ICreateTaskPresenter {
	IHomeView homeView;
	ITaskCreaterInteractor taskCreateInteractor;
	Map<String, String> data;
	private static final int INVALID_NAME = 1, INVALID_DESC = 2,
			INVALID_STAGE = 3;

	public CreateTaskPresenterImpl(IHomeView homeView) {
		super();
		this.homeView = homeView;
		taskCreateInteractor = new TaskCreateInteractorImpl(this);
	}

	@Override
	public void validateData(Map<String, String> data) {
		this.data = data;
		int invalidMemberId = 0;
		if (data.containsKey("taskName") && data.get("taskName").length() > 0) {
			if (data.containsKey("desc") && data.get("desc").length() > 0) {
				boolean isStageValid = false;
				for (String s : TaskActivity.stages) {
					if (data.get("selected stage").equals(s)) {
						isStageValid = true;
						taskCreateInteractor.createTask(data,
								(Activity) homeView);
						break;
					}
				}
				if (!isStageValid) {
					invalidMemberId = INVALID_STAGE;
				}
			} else {
				invalidMemberId = INVALID_DESC;
			}
		} else {
			invalidMemberId = INVALID_NAME;
		}
		if (invalidMemberId != 0) {
			homeView.onInvalidData(invalidMemberId);
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
