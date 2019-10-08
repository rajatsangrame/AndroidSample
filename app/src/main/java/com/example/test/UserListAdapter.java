package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<User> mUser; // Cached copy of words


    UserListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {

        if (mUser != null) {
            User current = mUser.get(position);
            holder.wordItemView.setText(current.getFirstName() + " " + current.getLastName()
                    + " " + current.getRollNumber());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No User");
        }
    }

    void setUser(List<User> user) {
        mUser = user;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mUser != null)
            return mUser.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private ViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
