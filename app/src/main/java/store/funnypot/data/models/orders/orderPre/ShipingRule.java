package store.funnypot.data.models.orders.orderPre;

import java.util.Date;

public class ShipingRule{
    public int id;
    public String name;
    public int shipping_id;
    public String type;
    public String from;
    public String myto;
    public String price;
    public Date created_at;
    public Date updated_at;
}
