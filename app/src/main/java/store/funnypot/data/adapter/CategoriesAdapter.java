package store.funnypot.data.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import store.funnypot.R;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.home.Category;
import store.funnypot.util.helper.Constants;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<Category> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public CategoriesAdapter(Context context, List<Category> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context =  (context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category single = mData.get(position);
        holder.myTextView.setText(single.name);
        if (single.icon_image !=null) {
            Glide.with(context).load(getImageUrl(single.icon_image)).into(holder.myImageView);

        }

    }

    private String getImageUrl(String iconImage) {

        return Constants.IMAGE_PATH+iconImage;
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.category_name);
            myImageView = itemView.findViewById(R.id.category_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Category getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}