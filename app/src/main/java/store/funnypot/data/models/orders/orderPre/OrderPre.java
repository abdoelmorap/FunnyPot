package store.funnypot.data.models.orders.orderPre;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import store.funnypot.data.models.cart.Item;

public class OrderPre {
    public String status;
    public ArrayList<Item> cart;
//    public ArrayList<Discount> discounts;
//    public ArrayList<Shipping> shipping;
//    public ArrayList<User> user;
   @SerializedName
  ("address") public ArrayList<UserAddress> userAddresses;
}
