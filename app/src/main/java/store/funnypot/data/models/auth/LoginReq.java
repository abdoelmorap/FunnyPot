package store.funnypot.data.models.auth;

import com.google.gson.annotations.SerializedName;

public class LoginReq {
    @SerializedName("phone") final private String phone;
    public LoginReq(String phone){
        this.phone=phone;
    }

}


