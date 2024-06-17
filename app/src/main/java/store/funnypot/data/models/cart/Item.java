package store.funnypot.data.models.cart;

import java.util.ArrayList;
import java.util.Date;

public class Item {
    public int id;
    public int product_id;
    public int qnty;
    public int user_id;
    public Date created_at;
    public Date updated_at;
    public ArrayList<ItemsDetails> item_details;

}
