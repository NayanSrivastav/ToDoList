package model;

import java.sql.Blob;

public class Task {
	private int id;
	private String name;
	private String description;
	private String currentStage;
	private byte[] taskImage;
	private TaskTag taskTag;
	private String deadline;
	private String endDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Task(String name, String description, String currentStage,
			byte[] taskImage, TaskTag taskTag, String deadline, String endDate) {
		super();
		this.name = name;
		this.description = description;
		this.currentStage = currentStage;
		this.taskImage = taskImage;
		this.taskTag = taskTag;
		this.deadline = deadline;
		this.endDate = endDate;
	}

	public Task(String name, String description, String currentStage,
			byte[] taskImage, TaskTag taskTag) {
		super();
		this.name = name;
		this.description = description;
		this.currentStage = currentStage;
		this.taskImage = taskImage;
		this.taskTag = taskTag;
	}

	public Task(String name, String description, String currentStage,
			byte[] taskImage) {
		super();
		this.name = name;
		this.description = description;
		this.currentStage = currentStage;
		this.taskImage = taskImage;
	}

	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

	public byte[] getTaskImage() {
		return taskImage;
	}

	public void setTaskImage(byte[] taskImage) {
		this.taskImage = taskImage;
	}

	public TaskTag getTaskTag() {
		return taskTag;
	}

	public void setTaskTag(TaskTag taskTag) {
		this.taskTag = taskTag;
	}

}
