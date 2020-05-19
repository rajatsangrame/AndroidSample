package com.example.daggerdemo.zomato.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.daggerdemo.R;
import com.example.daggerdemo.zomato.MainActivity;
import com.example.daggerdemo.zomato.model.RestaurantsItem;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private static final String TAG = "RestaurantAdapter";
    private RestaurantAdapterListener mListener;
    private final MainActivity mainActivity;
    private List<RestaurantsItem> mRestaurants;
    private boolean mIsSavedFragment = false;


    public RestaurantAdapter(MainActivity activity) {
        this.mainActivity = activity;
    }

    public void setmListener(RestaurantAdapterListener listener) {
        this.mListener = listener;
    }

    public void setIsSavedFragment(boolean isSavedFragment) {
        this.mIsSavedFragment = isSavedFragment;
    }

    public void setRestaurants(List<RestaurantsItem> mRestaurants) {
        if (mRestaurants != null) {
            this.mRestaurants = mRestaurants;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaurant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        RestaurantsItem item = mRestaurants.get(i);
        String thumbUrl = item.getRestaurant().getThumb();
        viewHolder.textView.setText(item.getRestaurant().getName());

        Glide.with(mainActivity).load(thumbUrl).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mRestaurants == null ? 0 : mRestaurants.size();
    }


    public interface RestaurantAdapterListener {
        void onItemClicked(RestaurantsItem restaurant);

        void onItemOptionsClicked(RestaurantsItem restaurant);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.tv_title);
            imageView = view.findViewById(R.id.iv_thumb);
        }

        @Override
        public void onClick(View v) {
            if (mListener == null) return;
            int index = this.getAdapterPosition();
            if (v instanceof FrameLayout) {
                mListener.onItemOptionsClicked(mRestaurants.get(index));
            } else {
                mListener.onItemClicked(mRestaurants.get(index));
            }
        }
    }
}
