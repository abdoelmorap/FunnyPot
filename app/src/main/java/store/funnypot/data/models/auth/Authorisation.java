package store.funnypot.data.models.auth;

public class Authorisation {
    private String token;
    private String type;


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
