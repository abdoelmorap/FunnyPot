package store.funnypot.data.apiFollowManger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.items.ItemsDetails;

public interface ApiFollowInterface {
    @GET("/products")
    public Call<ProductsModel> products();
    @GET("/public/api/home")
    public Call<HomeData> homeData();
    @GET("/public/api/items/{id}")
    public Call<ItemsDetails> ItemDetailsData(@Path("id") String id );

    @GET("/public/api/login")
    public Call<UserResponses> loginRetro(@Body LoginReq id);

}
