package store.funnypot.data.repoHandeller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import io.reactivex.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;
import store.funnypot.data.apiFollowManger.ApiFollowInterface;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.auth.User;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.cart.Cart;
import store.funnypot.data.models.cart.CartAdd;
import store.funnypot.data.models.items.ItemsDetails;
import store.funnypot.data.services.ServiceProvider;
import store.funnypot.ui.activities.ItemDetails;

public class RepoHandler  {


    public Maybe<ProductsModel> getDataOfProducts(){
    return  Maybe.create(emitter -> {
        ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
        Response  <ProductsModel> res= serviceProvider.products().execute();
        emitter.onSuccess(res.body());
        });
    }
    public Maybe<HomeData> getHomeDataRepo(){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response  <HomeData> res= serviceProvider.homeData().execute();
            emitter.onSuccess(res.body());
        });
    }
    public Maybe<ItemsDetails> ItemDetails(String id){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<ItemsDetails> res= serviceProvider.ItemDetailsData(id).execute();
            emitter.onSuccess(res.body());
        });
    }
    public Maybe<UserResponses> LoginFun(String phone){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<UserResponses> res= serviceProvider.loginRetro(new LoginReq(phone)).execute();
            emitter.onSuccess(res.body());
        });
    }
    public Maybe<UserResponses> UpdateName(String token, User user){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<UserResponses> res= serviceProvider.UpdateNameRetro(token,user).execute();
            if (res.isSuccessful()) {
                emitter.onSuccess(res.body());
            }else {
                emitter.onError(new Throwable(res.message()));
            }
        });
    }
    public Maybe<UserResponses> getProfileData(String token){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<UserResponses> res= serviceProvider.getProfile(token).execute();
            if (res.isSuccessful()) {
                emitter.onSuccess(res.body());
            }else {
                emitter.onError(new Throwable(res.message()));
            }
        });
    }

    public Maybe<Cart> AddItemToCart(String token, CartAdd add){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<Cart> res= serviceProvider.addtoCart(token,add).execute();
            if (res.isSuccessful()) {
                emitter.onSuccess(res.body());
            }else {
                emitter.onError(new Throwable(res.message()));
            }
        });
    }
    public Maybe<Cart> CartItems(String token){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<Cart> res= serviceProvider.cart(token).execute();
            if (res.isSuccessful()) {
                emitter.onSuccess(res.body());
            }else {
                emitter.onError(new Throwable(res.message()));
            }
        });
    }
}
