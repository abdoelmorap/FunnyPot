package store.funnypot.data.models.orders.orderPre;

import java.util.ArrayList;
import java.util.Date;

public class Cart{
    public int id;
    public int product_id;
    public int qnty;
    public int user_id;
    public Date created_at;
    public Date updated_at;
    public ArrayList<ItemDetail> item_details;
}
