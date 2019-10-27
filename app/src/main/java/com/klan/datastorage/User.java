package com.klan.datastorage;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
	@PrimaryKey
	public int uid;

	@ColumnInfo
	public String email;

	@ColumnInfo
	private String password;

	public String getPassword(){
		return password;
	}

	public void setPassword(String password) {
		password = password;
	}
}
