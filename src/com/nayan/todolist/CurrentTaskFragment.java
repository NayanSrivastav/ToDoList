package com.nayan.todolist;

import java.util.List;

import model.Task;
import adapter.TaskListAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class CurrentTaskFragment extends Fragment implements
		TaskListFragmentInterface {
	private ListView taskListView;
	List<Task> taskList;
	TaskListAdapter listAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.current_task_fragment,
				container, false);
		taskListView = (ListView) rootView.findViewById(R.id.lv_cur_tasks);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onLoad(String stage) {
		List<Task> temp = ((ToDoList) getActivity()).getTaskPresenter
				.gettaskList("started");
		taskList.clear();
		if (temp == null) {
			Toast.makeText(getActivity(), "No result found ", Toast.LENGTH_LONG)
					.show();
			onNoResult();
		} else {
			taskList.addAll(temp);
			Toast.makeText(getActivity(), "result found " + taskList.size(),
					Toast.LENGTH_LONG).show();
			onSuccess();
		}
	}

	@Override
	public void onNoResult() {

	}

	@Override
	public void onSuccess() {
		if (listAdapter == null) {
			listAdapter = new TaskListAdapter(taskList);
			taskListView.setAdapter(listAdapter);
		} else {
			listAdapter.notifyDataSetChanged();
		}
	}
}
