package model;

import java.sql.Blob;

public class Task {

	String name;
	String description;
	String currentStage;
	Blob taskImage;
	TaskTag taskTag;

	public Task(String name, String description, String currentStage,
			Blob taskImage, TaskTag taskTag) {
		super();
		this.name = name;
		this.description = description;
		this.currentStage = currentStage;
		this.taskImage = taskImage;
		this.taskTag = taskTag;
	}

	public Task(String name, String description, String currentStage,
			Blob taskImage) {
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

	public Blob getTaskImage() {
		return taskImage;
	}

	public void setTaskImage(Blob taskImage) {
		this.taskImage = taskImage;
	}

	public TaskTag getTaskTag() {
		return taskTag;
	}

	public void setTaskTag(TaskTag taskTag) {
		this.taskTag = taskTag;
	}

}
