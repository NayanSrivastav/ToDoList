package model;

public class TaskHistory {
	private int taskId;
	private String changes;
	private String date;

	public TaskHistory(int taskId, String changes, String date) {
		super();
		this.taskId = taskId;
		this.changes = changes;
		this.date = date;
	}

	public TaskHistory(String changes, String date) {
		super();
		this.changes = changes;
		this.date = date;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
