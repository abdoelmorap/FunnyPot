package store.funnypot.data.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import store.funnypot.R;
import store.funnypot.data.models.home.Slider;
import store.funnypot.util.helper.Constants;

public class ImageSwiperAdapter2 extends RecyclerView.Adapter<ImageSwiperAdapter2.ImageSwiper> {

    private List<Slider> list;
    private Context context;

    public ImageSwiperAdapter2(List<Slider> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageSwiper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slidingimages,
                parent, false);

        return new ImageSwiper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageSwiper holder, int position) {


        Glide.with(context.getApplicationContext())
                .load(Constants.IMAGE_PATH+list.get(position).image)
                .into(holder.imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.sliderText.setText(Html.fromHtml(list.get(position).title, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.sliderText.setText(Html.fromHtml(list.get(position).title));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageSwiper extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private TextView  sliderText;


        public ImageSwiper(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            sliderText= itemView.findViewById(R.id.slide_text);


        }


    }
}
