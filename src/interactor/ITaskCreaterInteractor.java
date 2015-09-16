package interactor;

import java.util.Map;

import android.content.Context;

public interface ITaskCreaterInteractor {
	public void createTask(Map<String, String> data, Context context);
}
