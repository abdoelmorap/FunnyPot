package store.funnypot.ui.activities.ui.fragments;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import store.funnypot.R;
import store.funnypot.base.BaseFragment;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.adapter.CategoriesAdapter;
import store.funnypot.data.adapter.ImageSwiperAdapter2;
import store.funnypot.data.adapter.ItemsAdapter;
import store.funnypot.data.models.auth.User;
import store.funnypot.data.models.home.Category;
import store.funnypot.data.models.home.MostViewed;
import store.funnypot.data.models.home.Slider;
import store.funnypot.data.sharedPref.SharedConfg;
import store.funnypot.databinding.FragmentMainBinding;
import store.funnypot.ui.activities.ItemDetails;
import store.funnypot.ui.activities.LogIn;
import store.funnypot.view.modelView.MainViewModel;


public class DashBoardFragment extends BaseFragment  implements ItemsAdapter.ItemClickListener {

    private MainViewModel mViewModel;
    FragmentMainBinding fragmentMainBinding;
    CategoriesAdapter categoriesAdapter;

    ItemsAdapter itemAdapterMostView;


    public static DashBoardFragment newInstance() {
        return new DashBoardFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentMainBinding.getRoot().setVisibility(View.GONE);
        mViewModel.getHomeData();
        mViewModel.HomeDataLive.observe( requireActivity(), (data)->{
        fragmentMainBinding.getRoot().setVisibility(View.VISIBLE);
        setDataOfCategory(data.categories);
        setDataOfPopularItems(data.mostViewed);
        setDataOfFeaturedItems(data.featured_items);
        setDataOfFavItems(data.fav_items);
        createSlider(data.sliders);
        });
        fragmentMainBinding.cartBtn.setOnClickListener(view1 -> {
         boolean IsLogin=new SharedConfg().isLogin(requireActivity());
         if(!IsLogin){

             Bundle options = ActivityOptions.makeCustomAnimation(requireActivity(),
                     R.anim.fade_in, R.anim.fade_out).toBundle();

             Intent intent = new Intent(requireActivity(), LogIn.class);

             requireActivity().startActivity(intent, options);
         }else {
         Toast.makeText(requireActivity(),"This Cart",Toast.LENGTH_LONG).show();
         }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        startRobot(requireActivity());

    }

    private void startRobot(FragmentActivity fragmentActivity) {
        String token=getToken(fragmentActivity);
        boolean IsLogin=new SharedConfg().isLogin(fragmentActivity);
if(IsLogin) {
    User user=new SharedConfg().getUser(fragmentActivity);
    String name= user.getName();
    String phone =user.getPhone();
    String email =user.getEmail();
        if (Objects.equals(name, phone + "NO_NAME")){

    View v = getLayoutInflater().inflate(R.layout.dailog_layout, null);
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragmentActivity).setView(v);
            AlertDialog OptionDialog = alertDialog.create();
            alertDialog.getContext().setTheme(R.style.RoundedCornersDialog);
            v.findViewById(R.id.etName).setVisibility(View.VISIBLE);
            v.findViewById(R.id.name_confirm).setVisibility(View.VISIBLE);
            v.findViewById(R.id.etEmail).setVisibility(View.GONE);
            v.findViewById(R.id.email_confirm).setVisibility(View.GONE);
v.findViewById(R.id.name_confirm).setOnClickListener(view -> {
     String nameMe=((EditText)v.findViewById(R.id.etName)).getText().toString();
   if (nameMe.isEmpty()) {
       Toast.makeText(requireActivity(), "Email Invalided", Toast.LENGTH_LONG).show();
   }else {
       user.setName(nameMe);
       mViewModel.updateUser(token, user);
       mViewModel.getUserMutableLiveData.observe(fragmentActivity, mDatat -> {
           new SharedConfg().saveUser(fragmentActivity, mDatat);
           OptionDialog.cancel();
       });
   }
});
            OptionDialog.show();

        }

else if (Objects.equals(email, phone + "@noMail.com")){

            View v = getLayoutInflater().inflate(R.layout.dailog_layout, null);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragmentActivity).setView(v);
            AlertDialog OptionDialog = alertDialog.create();

            alertDialog.getContext().setTheme(R.style.RoundedCornersDialog);
            v.findViewById(R.id.etName).setVisibility(View.GONE);
            v.findViewById(R.id.name_confirm).setVisibility(View.GONE);
            v.findViewById(R.id.etEmail).setVisibility(View.VISIBLE);
            v.findViewById(R.id.email_confirm).setVisibility(View.VISIBLE);
            v.findViewById(R.id.email_confirm).setOnClickListener(view -> {
                String myEmail=((EditText)v.findViewById(R.id.etEmail)).getText().toString();
                if(!myEmail.isEmpty()){
                    if (isValidEmailId(myEmail)){
                        user.setEmail(myEmail);
                        mViewModel.updateUser(token,user);
                        mViewModel.getUserMutableLiveData.observe(fragmentActivity,mDatat->{
                            new SharedConfg().saveUser(fragmentActivity,mDatat);
                            OptionDialog.cancel();
                        });
                    }else {
                        Toast.makeText(requireActivity(),"Email Invalided",Toast.LENGTH_LONG).show();
                    }
                }


            });
            OptionDialog.show();
}
}

    }



    private void createSlider(ArrayList<Slider> sliders) {
        if(sliders!=null) {
            ImageSwiperAdapter2 adapter2 = new ImageSwiperAdapter2(sliders, requireActivity());

            fragmentMainBinding.imageSlider.setAdapter(adapter2);

            fragmentMainBinding.imageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);


                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });
        }
    }

    private void setDataOfCategory(ArrayList<Category> categories) {
        // set up the RecyclerView
        if(categories!=null){  LinearLayoutManager ll=new LinearLayoutManager(requireActivity());
        ll.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentMainBinding.categoryRv.setLayoutManager(ll);
        categoriesAdapter = new CategoriesAdapter(requireActivity(), categories);
         fragmentMainBinding.categoryRv.setAdapter(categoriesAdapter);}

    }
    private void setDataOfFavItems(ArrayList<MostViewed> favItems) {
        // set up the RecyclerView
        if(favItems!=null){
        LinearLayoutManager ll=new GridLayoutManager(requireActivity(),2);

        fragmentMainBinding.myFavRv.setLayoutManager(ll);
        itemAdapterMostView = new ItemsAdapter(requireActivity(), favItems);
        fragmentMainBinding.myFavRv.setAdapter(itemAdapterMostView);}else {
            fragmentMainBinding.textView4.setVisibility(View.GONE);
            fragmentMainBinding.myFavRv.setVisibility(View.GONE);
            fragmentMainBinding.myFavRv.setNestedScrollingEnabled(false);

        }

    }
    @Override
    public void onItemClick(View view, int position,int uId) {
        Bundle options = ActivityOptions.makeCustomAnimation(requireActivity(),
                R.anim.fade_in, R.anim.fade_out).toBundle();

        Intent intent = new Intent(requireActivity(), ItemDetails.class);
        Log.d("TAG", "onItemClick: "+uId);
        intent.putExtra("id",uId+"");
        requireActivity().startActivity(intent, options);

    }
    private void setDataOfFeaturedItems(ArrayList<MostViewed> featiredItems) {
        // set up the RecyclerView
        if(featiredItems!=null){
        LinearLayoutManager ll=new GridLayoutManager(requireActivity(),2);

        fragmentMainBinding.featuredRv.setLayoutManager(ll);
        itemAdapterMostView = new ItemsAdapter(requireActivity(), featiredItems);
        itemAdapterMostView.setClickListener(this);
        fragmentMainBinding.featuredRv.setAdapter(itemAdapterMostView);
        fragmentMainBinding.featuredRv.setNestedScrollingEnabled(false);
        }

    }

    private void setDataOfPopularItems(ArrayList<MostViewed> PopularItems) {
        // set up the RecyclerView
        if(PopularItems!=null){

        LinearLayoutManager ll=new GridLayoutManager(requireActivity(),2);

        fragmentMainBinding.pupularRv.setLayoutManager(ll);
        itemAdapterMostView = new ItemsAdapter(requireActivity(), PopularItems);
        itemAdapterMostView.setClickListener(this);
        fragmentMainBinding.pupularRv.setAdapter(itemAdapterMostView);
        fragmentMainBinding.pupularRv.setNestedScrollingEnabled(false);

        }

    }

    @Override
    protected View getViewRoot() {
        fragmentMainBinding = FragmentMainBinding.inflate(getLayoutInflater(),null,false);

        return fragmentMainBinding.getRoot();
    }

    @Override
    protected BaseViewModel mbaseViewModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        return mViewModel;
    }

}