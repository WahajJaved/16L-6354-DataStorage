package com.klan.datastorage.db;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.klan.datastorage.Contact;
import com.klan.datastorage.R;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

	List<Contact> ContactList;
	Context mContext;

	public ContactAdapter(Context mContext, List<Contact> ContactList) {
		this.ContactList = ContactList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return this.ContactList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.ContactList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.fragment_contact_detail, null);

		TextView contactName = view.findViewById(R.id.contact_name);
		TextView contactEmail = view.findViewById(R.id.contact_email);
		TextView contactPhone = view.findViewById(R.id.contact_phone);
		ImageView contactImage = view.findViewById(R.id.contact_image);


		contactName.setText(ContactList.get(position).getName());
		contactImage.setImageURI(Uri.parse(ContactList.get(position).getPhotoUri()));
		contactPhone.setText(ContactList.get(position).getPhone());
		contactEmail.setText(ContactList.get(position).getEmail());
		view.setTag(ContactList.get(position));
		return view;
	}
}
