package database;

import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.TaskHistory;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

public final class DatabaseHandler extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "TaskDB";
	private static final int version = 1;
	private static final String TASK_TABLE_NAME = "task";

	public DatabaseHandler(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder createTaskTableScript = new StringBuilder("create table "
				+ TASK_TABLE_NAME + "(" + TASK_TABLE_NAME_COLUMNS[0]
				+ " integer primary key AUTOINCREMENT, ");
		for (int i = 1; i < TASK_TABLE_NAME_COLUMNS.length; i++) {
			createTaskTableScript.append(TASK_TABLE_NAME_COLUMNS[i]);
			if (i < TASK_TABLE_NAME_COLUMNS.length - 1) {
				createTaskTableScript.append(',');
			}
		}
		createTaskTableScript.append(");");

		StringBuilder createTaskHistoryTableScript = new StringBuilder(
				"create table " + TASK_HISTORY_TABLE_NAME + "(");
		for (int i = 0; i < TASK_HISTORY_TABLE_COLUMNS.length; i++) {
			createTaskHistoryTableScript.append(TASK_HISTORY_TABLE_COLUMNS[i]);
			if (i < TASK_HISTORY_TABLE_COLUMNS.length - 1) {
				createTaskHistoryTableScript.append(',');
			}
		}
		createTaskHistoryTableScript.append(");");
		db.execSQL(createTaskTableScript.toString());
		db.execSQL(createTaskHistoryTableScript.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TASK_TABLE_NAME);
		db.execSQL("drop table if exists " + TASK_HISTORY_TABLE_NAME);
		onCreate(db);
	}

	public Task getTask(int id) {
		SQLiteDatabase db = getReadableDatabase();
		Task task = null;
		Cursor dbCursor = db.query(true, TASK_TABLE_NAME,
				TASK_TABLE_NAME_COLUMNS, TASK_TABLE_NAME_COLUMNS[0] + "=?",
				new String[] { id + "" }, null, null, null, null);
		if (dbCursor != null) {
			dbCursor.moveToFirst();
			task = new Task(dbCursor.getString(1), dbCursor.getString(2));
			task.setCurrentStage(dbCursor.getString(3));
			task.setId(dbCursor.getInt(0));
			task.setTaskImage(dbCursor.getBlob(4));
			task.setDeadline(dbCursor.getString(6));
			task.setEndDate(dbCursor.getString(7));
		}
		return task;
	}

	@SuppressLint("NewApi")
	public List<TaskHistory> getHistoryOfTask(int taskId) {
		SQLiteDatabase db = getReadableDatabase();
		List<TaskHistory> historyList = new ArrayList<TaskHistory>();
		try {
			Cursor dbCursor = db.query(true, TASK_HISTORY_TABLE_NAME,
					TASK_HISTORY_TABLE_COLUMNS, TASK_HISTORY_TABLE_COLUMNS[0]
							+ " = ?", new String[] { taskId + "" }, null, null,
					null, null, null);
			if (dbCursor.moveToFirst()) {
				historyList = new ArrayList<TaskHistory>();
				do {
					String changes = dbCursor.getString(1);
					String changeDate = dbCursor.getString(2);
					historyList
							.add(new TaskHistory(taskId, changes, changeDate));
				} while (dbCursor.moveToNext());
			}
		} catch (Exception e) {
			return null;
		} finally {
			db.close();
		}
		return historyList;
	}

	public ArrayList<Task> getAllTasks(String stage) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor dbCursor = db.query(true, TASK_TABLE_NAME,
				TASK_TABLE_NAME_COLUMNS, TASK_TABLE_NAME_COLUMNS[3] + "=?",
				new String[] { stage }, null, null, null, null);
		ArrayList<Task> taskList = null;
		if (dbCursor.moveToFirst()) {
			taskList = new ArrayList<Task>();
			do {
				Task task = new Task(dbCursor.getString(1),
						dbCursor.getString(2));
				taskList.add(task);
				task.setCurrentStage(dbCursor.getString(3));
				task.setId(dbCursor.getInt(0));
				task.setTaskImage(dbCursor.getBlob(4));
				// yet to add tags
				task.setDeadline(dbCursor.getString(6));
				task.setEndDate(dbCursor.getString(7));
			} while (dbCursor.moveToNext());
		}
		dbCursor.close();
		return taskList;
	}

	private static final String TASK_HISTORY_TABLE_NAME = "task_history";
	private static final String[] TASK_TABLE_NAME_COLUMNS = { "id", "name",
			"description", "currentStage", "taskImage", "taskTag", "deadline",
			"endDate" };
	private static final String[] TASK_HISTORY_TABLE_COLUMNS = { "id",
			"changes", "date" };

	/**
	 * update the task information and maintain history in history table
	 * 
	 * @param task
	 * @param taskHistory
	 * @return
	 */
	public boolean updateTask(Task task, TaskHistory taskHistory) {
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try {

			ContentValues values = new ContentValues();
			values.put(TASK_TABLE_NAME_COLUMNS[1], task.getName());
			values.put(TASK_TABLE_NAME_COLUMNS[2], task.getDescription());
			values.put(TASK_TABLE_NAME_COLUMNS[3], task.getCurrentStage());
			values.put(TASK_TABLE_NAME_COLUMNS[6], task.getDeadline());
			db.update(TASK_TABLE_NAME, values, TASK_TABLE_NAME_COLUMNS[0]
					+ " = ? ", new String[] { task.getId() + "" });
			values = new ContentValues();
			values.put(TASK_HISTORY_TABLE_COLUMNS[0], task.getId() + "");
			values.put(TASK_HISTORY_TABLE_COLUMNS[1], taskHistory.getChanges());
			values.put(TASK_HISTORY_TABLE_COLUMNS[2], taskHistory.getDate());
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.endTransaction();
		}
		return true;
	}

	public boolean addTask(Task task) {
		try {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(TASK_TABLE_NAME_COLUMNS[1], task.getName());
			values.put(TASK_TABLE_NAME_COLUMNS[2], task.getDescription());
			values.put(TASK_TABLE_NAME_COLUMNS[3], task.getCurrentStage());
			String utilityString = "";
			if (task.getTaskImage() != null) {
				utilityString = Base64.encodeToString(task.getTaskImage(),
						Base64.DEFAULT);
			}
			values.put(TASK_TABLE_NAME_COLUMNS[4], utilityString);
			utilityString = "";
			if (task.getTaskTag() != null) {
				utilityString = task.getTaskTag().getId() + "";
			}
			values.put(TASK_TABLE_NAME_COLUMNS[5], utilityString);
			utilityString = "";
			if (task.getDeadline() != null) {
				utilityString = task.getDeadline();
			}
			values.put(TASK_TABLE_NAME_COLUMNS[6], utilityString);
			utilityString = "";
			if (task.getEndDate() != null) {
				utilityString = task.getEndDate();
			}
			values.put(TASK_TABLE_NAME_COLUMNS[7], utilityString);
			db.insert(TASK_TABLE_NAME, null, values);
			db.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
