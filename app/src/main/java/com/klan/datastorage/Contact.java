package com.klan.datastorage;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacts")
public class Contact {

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name="id")
	public int uid;

	@ColumnInfo(name="name")
	@NonNull
	public String name;

	@ColumnInfo(name="phone")
	public String phone;

	@ColumnInfo(name="email")
	public String email;

	@ColumnInfo(name = "image")
	private String photoUri;

	public int getUid() {
		return uid;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhotoUri() { return photoUri; }

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}

}
