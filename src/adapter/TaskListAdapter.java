package adapter;

import java.util.List;

import model.Task;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaskListAdapter extends BaseAdapter{
	List<Task> taskList;
	public TaskListAdapter(List<Task> taskList) {
		this.taskList=taskList;
	}

	@Override
	public int getCount() {
		
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
