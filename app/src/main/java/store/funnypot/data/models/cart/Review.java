package store.funnypot.data.models.cart;

import java.util.Date;

public class Review {
    public int id;
    public int customer_id;
    public int product_id;
    public int star;
    public String comment;
    public String status;
    public Date created_at;
    public Date updated_at;
    public Object images;
}
