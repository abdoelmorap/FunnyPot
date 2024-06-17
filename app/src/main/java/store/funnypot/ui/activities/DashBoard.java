package store.funnypot.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dagger.hilt.android.AndroidEntryPoint;
import store.funnypot.R;
import store.funnypot.base.BaseActivity;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.sharedPref.SharedConfg;
import store.funnypot.databinding.ActivityDashBoardBinding;
import store.funnypot.ui.activities.ui.fragments.CartFragment;
import store.funnypot.ui.activities.ui.fragments.DashBoardFragment;
import store.funnypot.ui.activities.ui.fragments.OrdersFragment;
import store.funnypot.ui.activities.ui.fragments.SettingsFragment;
import store.funnypot.view.modelView.MainViewModel;


@AndroidEntryPoint
public class DashBoard extends BaseActivity {
    ActivityDashBoardBinding activityDashBoardBinding;
    MainViewModel mainViewModel ;
    int currentSelected =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DashBoardFragment.newInstance())
                    .commitNow();
        }
        currentSelected= activityDashBoardBinding.bnv2.getSelectedItemId();
        activityDashBoardBinding.bnv2.setOnItemSelectedListener(item -> {
            if (currentSelected != item.getItemId()) {

                Log.d("TAG", "onCreate: " + (item.getItemId() == R.id.navHome) + " and " + (item.getItemId()));

                if (item.getItemId() == R.id.navHome) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, DashBoardFragment.newInstance())
                            .commitNow();
                } else if (item.getItemId() == R.id.navOrders) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, CartFragment.newInstance("", ""))
                            .commitNow();
                } else if (item.getItemId() == R.id.navMessage) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, OrdersFragment.newInstance("", ""))
                            .commitNow();
                    Log.d("TAG", "onNavigationItemSelected: Hi");
                } else if (item.getItemId() == R.id.navSettings) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, SettingsFragment.newInstance("", ""))
                            .commitNow();
                }


            }
            currentSelected= item.getItemId();
            return true;}
        );
        BadgeDrawable badge = activityDashBoardBinding.bnv2.getOrCreateBadge( R.id.navOrders);
        badge.setVisible(true);
        badge.setNumber(new SharedConfg().getCounterCart(DashBoard.this));

//        BadgeDrawable badge2 = activityDashBoardBinding.bnv2.getOrCreateBadge( R.id.navSettings);
//        badge2.setVisible(true);
//        badge2.setNumber(2);
    }

    @Override
    protected View getViewRoot() {
        activityDashBoardBinding = ActivityDashBoardBinding.inflate(getLayoutInflater(),null,false);
        return activityDashBoardBinding.getRoot();

    }

    @Override
    protected BaseViewModel mbaseViewModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        return mainViewModel;
    }
}