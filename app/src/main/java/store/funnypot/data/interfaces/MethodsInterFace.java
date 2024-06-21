package store.funnypot.data.interfaces;

import io.reactivex.Maybe;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.auth.User;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.cart.Cart;
import store.funnypot.data.models.cart.CartAdd;
import store.funnypot.data.models.items.ItemsDetails;
import store.funnypot.data.models.orders.orderPre.OrderPre;


public interface MethodsInterFace {
    Maybe<ProductsModel> getItems();
    Maybe<HomeData> getHomeData();
    Maybe<ItemsDetails> getItemDetails(String id);

    Maybe<UserResponses> logIn(String phone);

    Maybe<UserResponses> updateUserName(String token, User user);
    Maybe<UserResponses> getProfile(String token);
    Maybe<Cart> getCartItems(String token);
    Maybe<Cart> addToCart(String token, CartAdd cartAdd);
    Maybe<OrderPre> prepareForOrder(String token);
}
