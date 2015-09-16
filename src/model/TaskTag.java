package model;

import java.sql.Blob;

import android.provider.ContactsContract.Contacts;

public class TaskTag {
	int id;
	Blob[] images;
	Contacts[] persons;

	public Blob[] getImages() {
		return images;
	}

	public void setImages(Blob[] images) {
		this.images = images;
	}

	public Contacts[] getPersons() {
		return persons;
	}

	public void setPersons(Contacts[] persons) {
		this.persons = persons;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
