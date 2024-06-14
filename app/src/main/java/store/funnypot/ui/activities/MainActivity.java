package store.funnypot.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;

import java.security.Provider;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlin.reflect.KClass;
import store.funnypot.base.BaseActivity;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.databinding.ActivityMainBinding;
import store.funnypot.util.helper.ProgressHelper;
import store.funnypot.view.modelView.MainViewModel;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;
    MainViewModel mainViewModel ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, DashBoard.class);
                startActivity(i);
                finish();
            }
        }, 4000);

        }


    @Override
    protected View getViewRoot() {

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        return activityMainBinding.getRoot();
    }

    @Override
    protected BaseViewModel mbaseViewModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        return mainViewModel;
    }


}