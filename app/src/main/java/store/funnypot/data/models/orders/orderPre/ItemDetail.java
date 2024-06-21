package store.funnypot.data.models.orders.orderPre;

import java.util.ArrayList;
import java.util.Date;

public class ItemDetail{
    public int id;
    public String name;
    public String description;
    public Object content;
    public String status;
    public String images;
    public Object sku;
    public int order;
    public int quantity;
    public int allow_checkout_when_out_of_stock;
    public int with_storehouse_management;
    public int is_featured;
    public int brand_id;
    public int is_variation;
    public int sale_type;
    public int price;
    public int sale_price;
    public Date start_date;
    public Date end_date;
    public int length;
    public int wide;
    public int height;
    public int weight;
    public int tax_id;
    public int views;
    public Date created_at;
    public Date updated_at;
    public int stock_status;
    public int store_id;
    public int created_by_id;
    public String created_by_type;
    public int approved_by;
    public String image;
    public String product_type;
    public String barcode;
    public int cost_per_item;
    public int generate_license_code;
    public ArrayList<String> item_files;
}
