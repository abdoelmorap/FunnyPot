package store.funnypot.data.services;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import store.funnypot.data.apiFollowManger.ApiFollowInterface;
import store.funnypot.util.helper.Constants;


public enum ServiceProvider {
    INSTANCE;

     private static ApiFollowInterface mGithubService;

    public static ApiFollowInterface getGithubService() {
        // Lazy load a Github singleton
        if (mGithubService == null) {


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            httpClientBuilder.networkInterceptors().add(new RequestHeadersNetworkInterceptor(new HashMap<String, String>()
            {
                {

                    put("Accept", "application/json");
                    put("Content-Type", "application/json");

                }
            }));
            httpClientBuilder.interceptors().add(logging);
            OkHttpClient client =  httpClientBuilder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mGithubService =  retrofit.create(ApiFollowInterface.class);
             }

        return mGithubService;
    }
}