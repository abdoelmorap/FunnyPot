package store.funnypot.data.impl;

import android.util.Log;

import io.reactivex.Maybe;
import store.funnypot.data.interfaces.MethodsInterFace;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.items.ItemsDetails;
import store.funnypot.data.repoHandeller.RepoHandler;
import store.funnypot.ui.activities.ItemDetails;

public class RepoImpl extends RepoHandler  implements MethodsInterFace {




    @Override
    public Maybe<ProductsModel> getItems() {
        return getDataOfProducts();
    }

    @Override
    public Maybe<HomeData> getHomeData() {
        return getHomeDataRepo();
    }

    @Override
    public Maybe<ItemsDetails> getItemDetails(String id) {
        return ItemDetails(id);
    }

    @Override
    public Maybe<UserResponses> logIn(LoginReq loginReq) {
        return LoginFun(loginReq);
    }


}
