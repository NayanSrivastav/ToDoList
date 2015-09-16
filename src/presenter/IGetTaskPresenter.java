package presenter;

import java.util.List;

import model.Task;

public interface IGetTaskPresenter {
	public List<Task> gettaskList(String stage);
}
