package store.funnypot.data.interfaces;

import io.reactivex.Maybe;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.items.ItemsDetails;


public interface MethodsInterFace {
    Maybe<ProductsModel> getItems();
    Maybe<HomeData> getHomeData();
    Maybe<ItemsDetails> getItemDetails(String id);

    Maybe<UserResponses> logIn(LoginReq loginReq);


}
