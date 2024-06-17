package store.funnypot.data.models.auth;

import com.google.gson.annotations.SerializedName;

public class Authorisation {
    @SerializedName("token"  ) private String token;
    @SerializedName("type"  ) private String type;


    // Getter Methods

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }
}
