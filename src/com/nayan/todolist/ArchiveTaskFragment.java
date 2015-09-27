package com.nayan.todolist;

import java.util.ArrayList;
import java.util.List;

import model.Task;

import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ArchiveTaskFragment extends Fragment implements
		TaskListFragmentInterface, OnItemClickListener {
	private ListView taskListView;
	private TextView txtNoResultFound;
	List<Task> taskList;
	List<String> dataArray;
	ArrayAdapter<String> arrayAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.current_task_fragment,
				container, false);
		taskListView = (ListView) rootView.findViewById(R.id.lv_cur_tasks);
		txtNoResultFound = (TextView) rootView
				.findViewById(R.id.tv_curren_no_res);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		onLoad(TaskActivity.stages[2]);
		taskListView.setOnItemClickListener(this);
	}

	@Override
	public void onLoad(String stage) {
		new AsyncGetTasksByStage(stage,
				((ToDoList) getActivity()).getTaskPresenter, this).execute();
	}

	/**
	 * fills data array with content to be displayed as tasks to user in the
	 * listview
	 */
	private void fillDataArray() {
		if (dataArray == null) {
			dataArray = new ArrayList<String>();
		} else {
			dataArray.clear();
		}
		List<String> temp = new ArrayList<String>();
		for (Task t : taskList) {
			temp.add("\nTask Name: " + t.getName() + "\nCurrent Stage: "
					+ t.getCurrentStage() + "\n" + t.getDeadline());
		}
		dataArray.addAll(temp);
	}

	@Override
	public void onNoResult() {

		txtNoResultFound.setVisibility(View.VISIBLE);
	}

	@Override
	public void onSuccess(List<Task> taskList) {
		this.taskList = taskList;
		fillDataArray();
		if (arrayAdapter == null) {
			arrayAdapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, dataArray);
		}
		taskListView.setAdapter(arrayAdapter);

	}

	/**
	 * pass the selected task information to task history activity
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), TaskHistoryActivity.class);
		intent.putExtra("taskData", new Gson().toJson(taskList.get(position)));
		startActivity(intent);
	}
}
