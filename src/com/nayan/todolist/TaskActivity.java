package com.nayan.todolist;


import java.util.HashMap;
import java.util.Map;

import presenter.CreateTaskPresenterImpl;
import presenter.ICreateTaskPresenter;
import widgets.EditTextDatePicker;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends BaseActivity implements IHomeView,
		OnClickListener {
	
	private TextView textViewDeadline, taskStages[];
	private Button createTaskBtn;
	private EditText taskNameEdt, taskDescEdt;
	private String selectedStage;
	private static final String stages[] = { "Assigned", "Started", "Finished" };
	ICreateTaskPresenter taskPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task);
		super.setContext(this);
		// controls initialization
		textViewDeadline = (TextView) findViewById(R.id.deadline_tv);
		textViewDeadline.setOnClickListener(new EditTextDatePicker(
				TaskActivity.this, textViewDeadline.getId()));
		createTaskBtn = (Button) findViewById(R.id.create_task_btn);
		taskNameEdt = (EditText) findViewById(R.id.task_nm);
		taskDescEdt = (EditText) findViewById(R.id.task_desc);
		taskStages = new TextView[3];
		taskStages[0] = (TextView) findViewById(R.id.stage_assigned);
		taskStages[1] = (TextView) findViewById(R.id.stage_started);
		taskStages[2] = (TextView) findViewById(R.id.stage_finished);
		taskPresenter=new CreateTaskPresenterImpl(this);
		// setting events on controls
		createTaskBtn.setOnClickListener(this);
		for(int i=0;i<taskStages.length;i++)
		{
			taskStages[i].setOnClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public void openTask() {
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == createTaskBtn.getId()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("taskName", taskNameEdt.getText().toString());
			data.put("desc", taskDescEdt.getText().toString());
			data.put("EstDate", textViewDeadline.getText().toString());
			data.put("selected stage", selectedStage);
			taskPresenter.validateData(data);
		} else if (v.getId() == taskStages[0].getId()
				|| v.getId() == taskStages[1].getId()
				|| v.getId() == taskStages[2].getId()) {
			if (v.getId() == taskStages[0].getId()) {
				selectedStage = stages[0];
				taskStages[0].setText(Html.fromHtml("<b>" + selectedStage
						+ "</b>"));
				taskStages[1].setText(stages[1]);
				taskStages[2].setText(stages[2]);
			} else if (v.getId() == taskStages[1].getId()) {
				selectedStage = stages[1];
				taskStages[1].setText(Html.fromHtml("<b>" + selectedStage
						+ "</b>"));
				taskStages[0].setText(stages[0]);
				taskStages[2].setText(stages[2]);
			} else if (v.getId() == taskStages[2].getId()) {
				selectedStage = stages[2];
				taskStages[2].setText(Html.fromHtml("<b>" + selectedStage
						+ "</b>"));
				taskStages[1].setText(stages[1]);
				taskStages[0].setText(stages[0]);
			}
		}
	}

	@Override
	public void onSuccess() {
		onBackPressed();
	}

	@Override
	public void onFailure() {
		Toast.makeText(this, "some error occured", Toast.LENGTH_LONG).show();
	}

}
