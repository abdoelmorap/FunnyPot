package store.funnypot.data.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import store.funnypot.R;
import store.funnypot.data.models.home.MostViewed;
import store.funnypot.util.helper.Constants;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<MostViewed> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ItemsAdapter(Context context, List<MostViewed> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context =  (context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.small_card_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MostViewed single = mData.get(position);
        holder.titleTextView.setText(single.name);

        holder.priceTextView.setText((single.sale_price == 0 ?  single.price:single.sale_price)+"LE");

            try {
                String image=new JSONArray(single.images).get(0).toString();

                Glide.with(context).load(getImageUrl(single.image==null || single.image.isEmpty() ? (image):  single.image)).into(holder.myImageView);
            } catch (JSONException e) {
                throw new RuntimeException(e);
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
        TextView titleTextView;
        TextView priceTextView;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_name);
            priceTextView = itemView.findViewById(R.id.price_amount);
            myImageView = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition(),mData.get(getAdapterPosition()).id);
        }
    }

    // convenience method for getting data at click position
    MostViewed getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position,int uId);
    }
}