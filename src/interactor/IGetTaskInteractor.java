package interactor;

import java.util.List;

import android.content.Context;
import model.Task;

public interface IGetTaskInteractor {
	public List<Task> getTask(String stage, Context context);
}
