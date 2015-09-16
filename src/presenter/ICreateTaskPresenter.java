package presenter;

import java.util.Map;

public interface ICreateTaskPresenter {
	public void validateData(Map<String, String> data);

	public void onSuccess();

	public void onFailure();

	public void onDataError();
}
