package com.klan.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void getContacts() {
		final ContentResolver cr = getContentResolver();
		String[] projection = new String[] {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
				ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO};
		final Cursor cursor = cr.query(ContactsContract.Data.CONTENT_URI, projection, null, null, null);

	}
}
