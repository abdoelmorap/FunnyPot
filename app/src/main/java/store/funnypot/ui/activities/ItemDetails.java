package store.funnypot.ui.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.emoji2.text.EmojiCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import store.funnypot.R;
import store.funnypot.base.BaseActivity;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.models.items.Item;
import store.funnypot.data.models.items.ItemsDetails;
import store.funnypot.data.sharedPref.SharedConfg;
import store.funnypot.data.sharedPref.SharedVariables;
import store.funnypot.databinding.ActivityItemDetailsBinding;
import store.funnypot.databinding.ActivityMainBinding;
import store.funnypot.util.helper.Constants;
import store.funnypot.view.modelView.MainViewModel;

public class ItemDetails extends BaseActivity {
 MainViewModel mainViewModel;
 int count =1;
 ArrayList<Integer> cartItems=new ArrayList<>();
 ActivityItemDetailsBinding activityItemDetailsBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityItemDetailsBinding.getRoot().setVisibility(View.GONE);
        String Uid =getIntent().getStringExtra("id");
        Log.d("TAG", "onCreate: Uid"+Uid);
        mainViewModel.getItemDetails(Uid);
        mainViewModel.itemDetailsMutableLiveData.observe(this,(data)->{
            try {
                setDataOfItem(data.items);

                activityItemDetailsBinding.getRoot().setVisibility(View.VISIBLE);
            } catch (JSONException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            setCounterBtn(data,ItemDetails.this);
        });
        mainViewModel.getCartItems(getToken(ItemDetails.this));
        mainViewModel.mCartDetailsMutableLiveData.observe(ItemDetails.this,(madata)->{
            for (store.funnypot.data.models.cart.Item item : madata.items) {
                if ((item.product_id + "").equals(Uid)){
                    activityItemDetailsBinding.addToCartBtn.setText("Update Cart");
                    count=item.qnty;
                    activityItemDetailsBinding.countTv.setText(count+"");

                }
                new SharedConfg().updateCounterCart(ItemDetails.this,madata.items.size());

            }



        });
    }
    void setCounterBtn(ItemsDetails data, Activity activity){
        cartItems= new SharedConfg().getCart(activity);


        activityItemDetailsBinding.countTv.setText(count+"");
        activityItemDetailsBinding.addMore.setOnClickListener(view ->
        {
if (count<10) {
    count++;
}
activityItemDetailsBinding.countTv.setText(count+"");
        });
        activityItemDetailsBinding.removeMore.setOnClickListener(view ->
        {
            if (count!=1){
                count--;
            }
            activityItemDetailsBinding.countTv.setText(count+"");
        });
activityItemDetailsBinding.addToCartBtn.setOnClickListener(view ->
{
    String token = getToken(activity);
    mainViewModel.addToCart(token,data.items.id,count,ItemDetails.this);

});
    }
    private void setDataOfItem(Item item) throws JSONException, UnsupportedEncodingException {
        activityItemDetailsBinding.titleProduct.setText(item.name);
        activityItemDetailsBinding.rateBox.setRating(4.8f);
        activityItemDetailsBinding.priceProduct.setText((item.sale_price==0?item.price:item.sale_price)+" EG");

            String title = StringEscapeUtils.unescapeJava(item.content);
            String encodedHtml = Base64.encodeToString(title.getBytes(),
                    Base64.NO_PADDING);

        activityItemDetailsBinding.descDetails.getSettings().setDefaultFontSize(16);

        activityItemDetailsBinding.descDetails.getSettings().setSerifFontFamily("sans");

        activityItemDetailsBinding.descDetails.getSettings().setJavaScriptEnabled(true);

        activityItemDetailsBinding.descDetails.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        view.loadUrl(
                                "javascript:document.body.style.setProperty(\"color\", \"white\");"
                        );
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        view.loadUrl(
                                "javascript:document.body.style.setProperty(\"color\", \"black\");"
                        );
                        break;
                }




                view.loadUrl(
                        "javascript:document.body.style.setProperty(\"fontSize\", \"25px\");"
                );
                view.loadUrl(
                        "javascript:document.body.style.setProperty(\"text-align\", \"right\");"
                );
            }
        });

         activityItemDetailsBinding.descDetails.loadData(encodedHtml, "text/html", "base64");
        activityItemDetailsBinding.descDetails.setBackgroundColor(Color.TRANSPARENT);




        String image=new JSONArray(item.images).get(0).toString();

        Glide.with(this).load(getImageUrl(item.image==null || item.image.isEmpty() ? (image):  item.image)).into(activityItemDetailsBinding.mainImage);

         if (item.images.size() ==3){
        Glide.with(this).load(getImageUrl(item.images.get(0))).into(activityItemDetailsBinding.sec);
            activityItemDetailsBinding.sec.setOnClickListener(click->{
                Glide.with(this).load(getImageUrl(item.images.get(0))).into(activityItemDetailsBinding.mainImage);

            });
            Glide.with(this).load(getImageUrl(item.images.get(1))).into(activityItemDetailsBinding.sec2);
            activityItemDetailsBinding.sec2.setOnClickListener(click->{
                Glide.with(this).load(getImageUrl(item.images.get(1))).into(activityItemDetailsBinding.mainImage);

            });
            Glide.with(this).load(getImageUrl(item.images.get(2))).into(activityItemDetailsBinding.sec3);
            activityItemDetailsBinding.sec3.setOnClickListener(click->{
                Glide.with(this).load(getImageUrl(item.images.get(2))).into(activityItemDetailsBinding.mainImage);

            });
        }else  if (item.images.size() ==2){
            Glide.with(this).load(getImageUrl(item.images.get(0))).into(activityItemDetailsBinding.sec);
            activityItemDetailsBinding.sec.setOnClickListener(click->{
                Glide.with(this).load(getImageUrl(item.images.get(0))).into(activityItemDetailsBinding.mainImage);

            });
            Glide.with(this).load(getImageUrl(item.images.get(1))).into(activityItemDetailsBinding.sec2);
            activityItemDetailsBinding.sec2.setOnClickListener(click->{
                Glide.with(this).load(getImageUrl(item.images.get(1))).into(activityItemDetailsBinding.mainImage);

            });
            activityItemDetailsBinding.sec3.setVisibility(View.GONE);
        }else  if (item.images.size() ==1){
            Glide.with(this).load(getImageUrl(item.images.get(0))).into(activityItemDetailsBinding.sec);
            activityItemDetailsBinding.sec.setOnClickListener(click->{
                Glide.with(this).load(getImageUrl(item.images.get(0))).into(activityItemDetailsBinding.mainImage);
            });
            activityItemDetailsBinding.sec2.setVisibility(View.GONE);
            activityItemDetailsBinding.sec3.setVisibility(View.GONE);

        }


    }
    private String getImageUrl(String iconImage) {

        return Constants.IMAGE_PATH+iconImage;
    }
    @Override
    protected View getViewRoot() {
        activityItemDetailsBinding = ActivityItemDetailsBinding.inflate(getLayoutInflater(),null,false);
        return activityItemDetailsBinding.getRoot();
    }

    @Override
    protected BaseViewModel mbaseViewModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        return mainViewModel;
    }
}