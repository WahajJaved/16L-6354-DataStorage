package com.klan.datastorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.Manifest;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.klan.datastorage.db.ContactsDatabase;
import com.klan.datastorage.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

	public static FragmentManager fragmentManager;
	public static ContactsDatabase database;

	private SharedPreferences loginPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		fragmentManager = getSupportFragmentManager();
		database = ContactsDatabase.getDatabase(getApplicationContext());

		loginPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
		ContactsDatabase database = ContactsDatabase.getDatabase(getApplicationContext());


		if(findViewById(R.id.framgent_container) != null) {
			if(savedInstanceState != null)
				return;
			fragmentManager.beginTransaction().add(R.id.framgent_container, new HomeFragment()).addToBackStack(null).commit();
		}

		if (!loginPreferences.getBoolean("saved", false)) {
			database.getContactDAO().clearContacts();
			showContacts();
		}
	}


	public void showContacts() {    // Check the SDK version and whether the permission is already granted or not.

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
		} else getContacts();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

		if (requestCode == 100) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// Permission is granted
				getContacts();
			} else {
				Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
			}
		}
	}


	private void getContacts() {
		final ContentResolver cr = getContentResolver();
		final Cursor cursor = cr.query(ContactsContract.Data.CONTENT_URI, null, null, null, null);

		ArrayList<Contact> contacts = new ArrayList<>();
		if (cursor != null && cursor.getCount()>0) {
			while (cursor.moveToNext()) {
				Contact contact = new Contact();
				String uid = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
				Integer hasPhone = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

				contact.setUid(Integer.parseInt(uid));
				contact.setName(name);

				if (photoUri != null)
					contact.setPhotoUri(photoUri);

				// get the user's email address
				String email = null;
				Cursor ce = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
						ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
						new String[]{uid}, null);
				if (ce != null && ce.moveToFirst()) {
					email = ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
					ce.close();
				}

				if (email != null)
					contact.setEmail(email);

				String phone = null;
				if (hasPhone > 0) {
					Cursor cp = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
							new String[]{uid}, null);
					if (cp != null && cp.moveToFirst()) {
						phone = cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						contact.setPhone(phone);
					}
					cp.close();
				}
				if (phone != null) {
					contact.setPhone(phone);
					contacts.add(contact);
					database.getContactDAO().insert(contact);
				}
			}
		}
		cursor.close();
	}
}
