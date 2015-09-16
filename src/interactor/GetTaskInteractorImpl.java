package interactor;

import java.util.List;

import database.DatabaseHandler;
import android.content.Context;
import model.Task;

public class GetTaskInteractorImpl implements IGetTaskInteractor {

	@Override
	public List<Task> getTask(String stage, Context context) {
		DatabaseHandler dbHandler =new DatabaseHandler(context);
		return dbHandler.getAllTasks(stage);
	}

}
