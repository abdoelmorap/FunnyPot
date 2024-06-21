package store.funnypot.data.models.orders.orderPre;

import java.util.Date;

public class User {
    public int id;
    public String name;
    public String email;
    public String avatar;
    public String dob;
    public String phone;
    public Date created_at;
    public Date updated_at;
    public Date confirmed_at;
    public String email_verify_token;
    public int is_vendor;
    public Date vendor_verified_at;
    public String status;
    public String private_notes;
}
