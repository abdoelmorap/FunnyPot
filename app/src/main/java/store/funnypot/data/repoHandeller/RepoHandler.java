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
import store.funnypot.data.models.auth.UserResponses;
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
    public Maybe<UserResponses> LoginFun(LoginReq id){
        return  Maybe.create(emitter -> {
            ApiFollowInterface serviceProvider =   ServiceProvider.getGithubService();
            Response<UserResponses> res= serviceProvider.loginRetro(id).execute();
            emitter.onSuccess(res.body());
        });
    }

}
