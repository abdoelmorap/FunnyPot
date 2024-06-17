package store.funnypot.data.apiFollowManger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.auth.User;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.cart.Cart;
import store.funnypot.data.models.cart.CartAdd;
import store.funnypot.data.models.items.ItemsDetails;

public interface ApiFollowInterface {
    @GET("/products")
    public Call<ProductsModel> products();
    @GET("/public/api/home")
    public Call<HomeData> homeData();
    @GET("/public/api/items/{id}")
    public Call<ItemsDetails> ItemDetailsData(@Path("id") String id );


    @POST("/public/api/phoneAuth")
    public Call<UserResponses> loginRetro(@Body LoginReq phone);


    @PUT("/public/api/update/user")
    public Call<UserResponses> UpdateNameRetro(@Header("Authorization") String token, @Body
    User user);

    @GET("/public/api/user/profile")
    public Call<UserResponses> getProfile(@Header("Authorization") String token);

    @GET("/public/api/cart")
    public Call<Cart> cart(@Header("Authorization") String token);
    @POST("/public/api/cart")
    public Call<Cart> addtoCart(@Header("Authorization") String token, @Body CartAdd cartAdd);
}
