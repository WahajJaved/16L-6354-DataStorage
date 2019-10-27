package com.klan.datastorage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.klan.datastorage.fragments.ContactDetailFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
	private List<Contact> contactList;

	public ContactAdapter(List<Contact> contacts) {
		this.contactList = contacts;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		View contactView = inflater.inflate(R.layout.contact_card, parent, false);
		ViewHolder viewHolder = new ViewHolder(contactView);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Contact contact = contactList.get(position);
		holder.setContact(contact);
		holder.nameView.setText(contact.getName());

		if (contact.getPhotoUri() != null)
			holder.imageView.setImageURI(Uri.parse(contact.getPhotoUri()));

		if (contact.getPhotoUri() != null)
			holder.imageView.setImageURI(Uri.parse(contact.getPhotoUri()));
		else
			holder.imageView.setImageURI(Uri.parse("android.resource://com.klan.datastorage/" + R.drawable.ic_launcher_foreground));

	}

	@Override
	public int getItemCount() {
		return contactList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView nameView;
		public ImageView imageView;
		public CardView cardView;
		private Contact contact;

		public ViewHolder(@NonNull View ItemView) {
			super(ItemView);
			nameView = itemView.findViewById(R.id.contact_card_name);
			imageView = itemView.findViewById(R.id.contact_card_image);
			cardView = itemView.findViewById(R.id.contact_card);

			cardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString("Name", contact.getName());
					bundle.putString("Number", contact.getPhone());
					bundle.putInt("Id", contact.getUid());

					if (contact.getEmail() != null)
						bundle.putString("Email", contact.getEmail());
					else
						bundle.putString("Email", "Email Not Available");

					if (contact.getPhotoUri() != null)
						bundle.putString("Image", contact.getPhotoUri());
					else
						bundle.putString("Image", "android.resource://com.klan.datastorage/" + R.drawable.ic_launcher_foreground);

					ContactDetailFragment detailFragment = new ContactDetailFragment();
					detailFragment.setArguments(bundle);
					HomeActivity.fragmentManager.beginTransaction().replace(R.id.framgent_container, detailFragment).addToBackStack(null).commit();

				}
			});
		}
		public void setContact(Contact contact) {
			this.contact = contact;
		}
	}


}
