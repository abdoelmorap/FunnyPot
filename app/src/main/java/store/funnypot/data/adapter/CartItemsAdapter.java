package store.funnypot.data.adapter;


import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.Objects;

import store.funnypot.R;
import store.funnypot.data.models.cart.Item;
import store.funnypot.data.models.cart.ItemsDetails;
import store.funnypot.util.helper.Constants;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    private List<Item> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public CartItemsAdapter(Context context, List<Item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context =  (context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_raw_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemsDetails single = mData.get(position).item_details.get(0);
        holder.titleTextView.setText(single.name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.descTV.setText(Html.fromHtml(single.description,Html.FROM_HTML_MODE_COMPACT));
        }else {
            holder.descTV.setText(Html.fromHtml(single.description));

        }


        holder.priceTextView.setText((single.sale_price == 0 ?  single.price:single.sale_price)+"LE x "
        +mData.get(position).qnty);
        Glide.with(context).load(getImageUrl(single.image)).into(holder.myImageView);

            try {
                if (!Objects.equals(single.images, "[]")) {
                    String image = new JSONArray(single.images).get(0).toString();

                    Glide.with(context).load(getImageUrl(single.image == null || single.image.isEmpty() ? (image) : single.image)).into(holder.myImageView);
                }
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
        TextView descTV;
        TextView priceTextView;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_name);
            descTV = itemView.findViewById(R.id.discrbition);
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
    Item getItem(int id) {
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