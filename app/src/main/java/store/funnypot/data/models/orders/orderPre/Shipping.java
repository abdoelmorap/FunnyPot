package store.funnypot.data.models.orders.orderPre;

import java.util.ArrayList;
import java.util.Date;

public class Shipping{
    public int id;
    public String title;
    public String country;
    public Date created_at;
    public Date updated_at;
    public ArrayList<ShipingRule> shiping_rules;
}
