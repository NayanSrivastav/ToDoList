package model;

import java.sql.Blob;

import android.provider.ContactsContract.Contacts;

public class TaskTag {
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
}
