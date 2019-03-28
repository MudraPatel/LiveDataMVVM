package com.assignment.livedata.listItems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.livedata.R;
import com.assignment.livedata.db.Address;
import com.assignment.livedata.db.UserModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<UserModel> userModelList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        UserModel model = userModelList.get(position);
        holder.itemTextView.setText(String.format(context.getResources().getString(R.string.username) +  " %s" , model.getUsername()));
        holder.nameTextView.setText(String.format(context.getResources().getString(R.string.personname) +  " %s" , model.getPersonName()));
        holder.emailTextView.setText(String.format(context.getResources().getString(R.string.email) +  " %s" , model.getEmailId()));
        holder.phoneTextView.setText(String.format(context.getResources().getString(R.string.phone) +  " %s" , model.getPhone()));
        holder.websiteTextView.setText(String.format(context.getResources().getString(R.string.website) +  " %s" , model.getWebsite()));
        Address addressModel = model.getAddress();
        String address = addressModel.getStreet() + ", " + addressModel.getSuite() + ", "+addressModel.getCity() +", "+addressModel.getZipcode();
        holder.addressTextView.setText(String.format(context.getResources().getString(R.string.address) + " %s", address));
        holder.itemView.setTag(model);
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public void addItems(List<UserModel> userModelList) {
        this.userModelList = userModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;
        private TextView websiteTextView;
        private TextView addressTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
            emailTextView = view.findViewById(R.id.emailTextView);
            phoneTextView = view.findViewById(R.id.phoneTextView);
            websiteTextView = view.findViewById(R.id.websiteTextView);
            addressTextView = view.findViewById(R.id.addressTextView);

        }
    }
}