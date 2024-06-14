package store.funnypot.data.models;

import java.util.ArrayList;

import store.funnypot.data.models.home.Category;
import store.funnypot.data.models.home.FavItem;
import store.funnypot.data.models.home.FeaturedItem;
import store.funnypot.data.models.home.MostViewed;
import store.funnypot.data.models.home.Slider;


public class HomeData{
    public String status;
    public ArrayList<Category> categories;
    public ArrayList<Slider> sliders;
    public ArrayList<MostViewed> mostViewed;
    public ArrayList<MostViewed> featured_items;
    public ArrayList<MostViewed> fav_items;
}

