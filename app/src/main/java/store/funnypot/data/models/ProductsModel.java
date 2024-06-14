package store.funnypot.data.models;

import com.google.gson.annotations.SerializedName;

public class ProductsModel {


             @SerializedName("id"         ) final int id        ;
            @SerializedName("title"       ) final String title  ;
            @SerializedName("price"       ) final Double price     ;
            @SerializedName("description" ) final String description ;
            @SerializedName("category"    ) final String category    ;
            @SerializedName("image"       ) final String image     ;
            @SerializedName("rating"      ) final Rating rating    ;

    public ProductsModel(int id, String title, Double price, String description, String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    static class Rating{

    @SerializedName("rate"  ) final Double rate ;
    @SerializedName("count" )final  int count;

        Rating(Double rate, int count) {
            this.rate = rate;
            this.count = count;
        }
    }
}
