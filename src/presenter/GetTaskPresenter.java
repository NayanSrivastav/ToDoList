package presenter;

import interactor.GetTaskInteractorImpl;
import interactor.IGetTaskInteractor;

import java.util.List;

import android.app.Activity;

import com.nayan.todolist.IHomeView;

import database.DatabaseHandler;
import model.Task;

public class GetTaskPresenter implements IGetTaskPresenter {
	IHomeView iHomeView;
	IGetTaskInteractor iGetTaskInteractor;

	@Override
	public List<Task> gettaskList(String stage) {
		return iGetTaskInteractor.getTask(stage, (Activity) iHomeView);
	}

	public GetTaskPresenter(IHomeView iHomeView) {
		super();
		this.iHomeView = iHomeView;
		this.iGetTaskInteractor = new GetTaskInteractorImpl();
	}

}
