package com.nayan.todolist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import presenter.ITaskHistoryPressenter;
import presenter.TaskHistoryPresenter;
import widgets.TextDatePicker;
import com.google.gson.Gson;
import model.Task;
import model.TaskHistory;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskHistoryActivity extends BaseActivity implements
		OnClickListener, ITaskHistoryView {
	private Task task;
	private TextView tvTaskStage, tvTaskDeadline;
	private EditText tvTaskName, tvTaskDesc;
	private Button btnSaveChanges;
	private List<String> historyList;
	private ListView historyListView;
	private ArrayAdapter<String> historyAdapter;
	private ITaskHistoryPressenter iTaskHistoryPressenter;
	private TaskHistory taskHistory = null;
	private boolean isTaskChanged = false;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_history);
		Intent dataIntent = getIntent();
		Bundle dataBundle = dataIntent.getExtras();
		setContext(this);
		iTaskHistoryPressenter = new TaskHistoryPresenter(this);
		// ---------UI elements initialization------------//
		historyListView = (ListView) (findViewById(R.id.his_lv));
		tvTaskName = (EditText) (findViewById(R.id.task_name_));
		tvTaskDesc = (EditText) (findViewById(R.id.task_desc_));
		tvTaskStage = (TextView) (findViewById(R.id.stage_assigned_));
		btnSaveChanges = (Button) (findViewById(R.id.btn_task_save_changes));
		tvTaskDeadline = (TextView) (findViewById(R.id.deadline_tv_));
		task = new Gson()
				.fromJson(dataBundle.getString("taskData"), Task.class);
		// ---------End of UI elements initialization----//

		/*-----------------filling information into ui elements about
		task------------------*/
		tvTaskName.setText(task.getName());
		tvTaskDesc.setText(task.getDescription());
		tvTaskStage.setText(task.getCurrentStage());
		tvTaskDeadline.setText(task.getDeadline());
		/*-----------------end of filling information into ui elements about
		task----------*/

		// a task is modifiable as long as it is not finished
		if (task.getCurrentStage().equals(TaskActivity.stages[2])) {
			btnSaveChanges.setVisibility(View.GONE);
			tvTaskName.setEnabled(false);
			tvTaskDesc.setEnabled(false);
		} else {
			btnSaveChanges.setOnClickListener(this);
			tvTaskStage.setOnClickListener(this);
			tvTaskDeadline.setOnClickListener(new TextDatePicker(this,
					tvTaskDeadline.getId()));
		}

		// ---------Load history of task-----------//
		iTaskHistoryPressenter.loadHistory(task.getId());
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == btnSaveChanges.getId()) {

			StringBuilder changes = new StringBuilder();
			if (!task.getName().trim()
					.equals(tvTaskName.getText().toString().trim())) {
				changes.append("changed task name from "
						+ task.getName().trim() + " to "
						+ tvTaskName.getText().toString().trim() + "\n\n");
				task.setName(tvTaskName.getText().toString().trim());
			}
			if (!task.getDescription().trim()
					.equals(tvTaskDesc.getText().toString().trim())) {
				changes.append("old description: \" "
						+ task.getDescription().trim()
						+ " \" has been changed\n\n");
				task.setDescription(tvTaskDesc.getText().toString().trim());
			}
			if (!task.getCurrentStage().equals(tvTaskStage.getText())) {
				changes.append("task moved to " + tvTaskStage.getText()
						+ " from " + task.getCurrentStage() + "\n\n");
				task.setCurrentStage(tvTaskStage.getText().toString().trim());
			}
			if (!task.getDeadline().equals(tvTaskDeadline.getText())) {
				changes.append("Estimated date has been changed to "
						+ tvTaskDeadline.getText().toString().split(" ")[3]
								.trim() + " from "
						+ task.getDeadline().split(" ")[3].trim());
				task.setDeadline(tvTaskDeadline.getText().toString().trim());
			}
			if (changes.length() > 0)
				taskHistory = new TaskHistory(changes.toString(),
						new Date().toString());
			updateTask(taskHistory);
		} else if (v.getId() == tvTaskStage.getId()) {
			int taskStage = -1;
			for (int i = 0; i < TaskActivity.stages.length; i++) {
				if (TaskActivity.stages[i].equals(task.getCurrentStage())) {
					taskStage = i;
					break;
				}
			}
			AlertDialog.Builder stageChooser = new AlertDialog.Builder(this);
			stageChooser.setSingleChoiceItems(TaskActivity.stages, taskStage,
					null);
			stageChooser.setPositiveButton("Change Task Stage",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							tvTaskStage
									.setText(TaskActivity.stages[((AlertDialog) dialog)
											.getListView()
											.getCheckedItemPosition()]);
						}
					});
			stageChooser.show();
		}
	}

	@Override
	public void onSuccessFulLoad(List<TaskHistory> taskHistories) {
		historyList = new ArrayList<String>();
		for (int i = 0; i < taskHistories.size(); i++) {
			historyList.add(taskHistories.get(i).getChanges() + "\nDate :"
					+ taskHistories.get(i).getDate());
		}
		showHistory();
	}

	@Override
	public void onUpdateFail() {
		if (progressDialog != null & progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		Toast.makeText(this, "Your changes could not be saved! please retry",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showHistory() {
		if (historyAdapter == null) {
			historyAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, historyList);
			historyListView.setAdapter(historyAdapter);
		} else {
			historyAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void updateTask(TaskHistory taskHistory) {
		if (taskHistory != null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("Saving your changes");
			progressDialog.setCancelable(false);
			progressDialog.show();
			iTaskHistoryPressenter.updatetask(task, taskHistory);
		}
	}

	@Override
	public void onNoHistoryFound() {

	}

	@Override
	public void onSuccessFUlUpdate() {
		if (progressDialog != null & progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		Toast.makeText(this, "changes have been saved", Toast.LENGTH_SHORT)
				.show();
		String historyData = taskHistory.getChanges() + "\nDate: "
				+ taskHistory.getDate();
		if (historyList == null) {
			historyList = new ArrayList<String>();
			historyList.add(historyData);
		} else {
			historyList.add(0, historyData);
			isTaskChanged = true;
		}
		showHistory();
	}

	@Override
	public void onBackPressed() {
		if (isTaskChanged) {
			Intent intent = new Intent(this, ToDoList.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			finish();
			startActivity(intent);
		} else
			super.onBackPressed();
	}

}
