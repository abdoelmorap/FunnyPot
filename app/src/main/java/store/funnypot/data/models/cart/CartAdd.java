package store.funnypot.data.models.cart;

import com.google.gson.annotations.SerializedName;

public class CartAdd {
    @SerializedName("product_id") int product_id;
    @SerializedName("qnty") int qnty;

    public CartAdd(int product_id,int qnty){
        this.product_id=product_id;
        this.qnty=qnty;
    }
}
