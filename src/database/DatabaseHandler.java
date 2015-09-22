package database;

import java.util.ArrayList;

import model.Task;
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
				+ TASK_TABLE_NAME + "(");
		for (int i = 0; i < TASK_TABLE_NAME_COLUMNS.length; i++) {
			createTaskTableScript.append(TASK_TABLE_NAME_COLUMNS[i]);
			if (i < TASK_TABLE_NAME_COLUMNS.length - 1) {
				createTaskTableScript.append(",");
			}
		}
		createTaskTableScript.append(");");
		db.execSQL(createTaskTableScript.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TASK_TABLE_NAME);
		onCreate(db);
	}

	private static final String[] TASK_TABLE_NAME_COLUMNS = { "id", "name",
			"description", "currentStage", "taskImage", "taskTag", "deadline",
			"endDate" };

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
				values.put(TASK_TABLE_NAME_COLUMNS[7], utilityString);
			}
			int res = (int) db.insert(TASK_TABLE_NAME, null, values);
			db.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
