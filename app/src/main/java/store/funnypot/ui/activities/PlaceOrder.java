package store.funnypot.ui.activities;

import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import store.funnypot.R;
import store.funnypot.base.BaseActivity;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.adapter.CartItemsAdapter;
import store.funnypot.data.models.cart.Cart;
import store.funnypot.data.models.cart.Item;
import store.funnypot.data.models.orders.orderPre.UserAddress;
import store.funnypot.databinding.ActivityPlaceOrderBinding;
import store.funnypot.view.modelView.MainViewModel;

public class PlaceOrder extends BaseActivity {

    MainViewModel mainViewModel;
    ActivityPlaceOrderBinding activityPlaceOrderBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel.prepareForOrder(getToken(this));
        mainViewModel.orderPrepareLiveData.observe(this,data->{
            setDataOfCartItems(data.cart);
            setAddressData(data.userAddresses);

            setAllTotal(data.cart);
        });

    }
    private void setAllTotal(ArrayList<Item> data) {
        int allTotal=0;
        for(int i = 0; i < data.size(); i++){
            int sale_price= (data.get(i).item_details.get(0).sale_price==0? data.get(i).item_details.get(0).price:data.get(i).item_details.get(0).sale_price);
            int price=data.get(i).qnty * sale_price;
            allTotal=allTotal+price;
        }
        activityPlaceOrderBinding.subTotal.setText(allTotal+",00 EG");
        activityPlaceOrderBinding.taxFees.setText((allTotal*3/100)+",00 EG");
        activityPlaceOrderBinding.deliveryFees.setText((30)+",00 EG");
        activityPlaceOrderBinding.allTotal.setText((30)+allTotal+(allTotal*10/100)+",00 EG");
    }
    private void setAddressData(List<UserAddress> userAddresses) {
        if(userAddresses!=null){
            if (!userAddresses.isEmpty()){
            UserAddress userAddress=userAddresses.get(0);
        activityPlaceOrderBinding.etAddress.setText(userAddress.address);
        activityPlaceOrderBinding.etCountry.setText(userAddress.country);
        activityPlaceOrderBinding.etCity.setText(userAddress.city);
        activityPlaceOrderBinding.etState.setText(userAddress.state);
        }}
    }

    private void setDataOfCartItems(ArrayList<Item> items) {
        // set up the RecyclerView
        if(items!=null){
            LinearLayoutManager ll=new LinearLayoutManager(this);
            activityPlaceOrderBinding.cartRv.setLayoutManager(ll);
            CartItemsAdapter categoriesAdapter = new CartItemsAdapter(this, items);
            activityPlaceOrderBinding.cartRv.setAdapter(categoriesAdapter);}

    }


    @Override
    protected View getViewRoot() {

        activityPlaceOrderBinding=  ActivityPlaceOrderBinding.inflate(getLayoutInflater(),null,false);
        return activityPlaceOrderBinding.getRoot();
    }


    @Override
    protected BaseViewModel mbaseViewModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        return mainViewModel;
    }}