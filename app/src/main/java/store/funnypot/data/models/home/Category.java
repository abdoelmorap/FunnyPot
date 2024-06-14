package store.funnypot.data.models.home;

import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class Category {
    public int id;
    public String name;
    public int parent_id;
    public Object description;
    public String status;
    public int order;
    public String image;
    public int is_featured;
    public Date created_at;
    public Date updated_at;
    public String icon;
    public String icon_image;
}
