package store.funnypot.base;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.FontRequest;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;

import store.funnypot.R;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.util.helper.ProgressHelper;

public abstract class BaseActivity extends AppCompatActivity {
   BaseViewModel baseViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewRoot());
        baseViewModel=mbaseViewModel();
        final FontRequest fontRequest = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs);
        EmojiCompat.init(new FontRequestEmojiCompatConfig(getApplicationContext(), fontRequest)
                .setReplaceAll(true)
                .registerInitCallback(new EmojiCompat.InitCallback() {
                    @Override
                    public void onInitialized() {
                        Log.i("TAG", "EmojiCompat initialized");
                    }

                    @Override
                    public void onFailed(@Nullable Throwable throwable) {
                        Log.e("TAG", "EmojiCompat initialization failed", throwable);
                    }
                }));

        baseViewModel.loadingLiveData.observe(this, (data)->{

            if (data){
                ProgressHelper.showDialog(this,"Loading");
            }else {
                ProgressHelper.dismissDialog();
            }});

    }
    protected abstract View getViewRoot();

    protected abstract BaseViewModel mbaseViewModel();

}
