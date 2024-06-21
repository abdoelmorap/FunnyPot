package store.funnypot.ui.activities.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import store.funnypot.R;
import store.funnypot.base.BaseFragment;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.adapter.CartItemsAdapter;
import store.funnypot.data.models.cart.Cart;
import store.funnypot.data.models.cart.Item;
import store.funnypot.data.sharedPref.SharedConfg;
import store.funnypot.databinding.FragmentCartBinding;
import store.funnypot.ui.activities.ItemDetails;
import store.funnypot.ui.activities.PlaceOrder;
import store.funnypot.view.modelView.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainViewModel mViewModel;

    FragmentCartBinding fragmentCartBinding;


    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getCartItems(getToken(requireActivity()));
        mViewModel.mCartDetailsMutableLiveData.observe(requireActivity(),data->{
            setDataOfCartItems(data.items);
            setAllTotal(data);
            new SharedConfg().updateCounterCart(requireActivity(),data.items.size());
        });

    }

    private void setAllTotal(Cart data) {
        int allTotal=0;
        for(int i = 0; i < data.items.size(); i++){
            int sale_price= (data.items.get(i).item_details.get(0).sale_price==0? data.items.get(i).item_details.get(0).price:data.items.get(i).item_details.get(0).sale_price);
            int price=data.items.get(i).qnty * sale_price;
            allTotal=allTotal+price;
        }
        fragmentCartBinding.allTotal.setText(allTotal+",00-EG");
        fragmentCartBinding.checkoutBTN.setOnClickListener(view ->
        {
            startActivity(new Intent(requireActivity(), PlaceOrder.class));
        });
    }

    private void setDataOfCartItems(ArrayList<Item> items) {
        // set up the RecyclerView
        if(items!=null){
            LinearLayoutManager ll=new LinearLayoutManager(requireActivity());
            fragmentCartBinding.cartRv.setLayoutManager(ll);
            CartItemsAdapter categoriesAdapter = new CartItemsAdapter(requireActivity(), items);
            fragmentCartBinding.cartRv.setAdapter(categoriesAdapter);}

    }

    @Override
    protected View getViewRoot() {


        fragmentCartBinding =FragmentCartBinding.inflate(getLayoutInflater(),null,false);
        return  fragmentCartBinding.getRoot();

    }

    @Override
    protected BaseViewModel mbaseViewModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        return mViewModel;
    }
}