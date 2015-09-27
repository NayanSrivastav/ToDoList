package com.nayan.todolist;

public interface IHomeView {
	void onSuccess();
	void onFailure();
	void onInvalidData(int memberId);
}
