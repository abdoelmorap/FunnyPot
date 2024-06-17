package store.funnypot.data.models.auth;


import com.google.gson.annotations.SerializedName;

public class UserResponses {
    @SerializedName("status"  ) private String status;
    @SerializedName("user"  ) User UserObject;
    @SerializedName("authorisation"  ) Authorisation AuthorisationObject;


    // Getter Methods

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return UserObject;
    }

    public Authorisation getAuthorisation() {
        return AuthorisationObject;
    }

    // Setter Methods

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setUser( User userObject ) {
        this.UserObject = userObject;
    }

    public void setAuthorisation( Authorisation authorisationObject ) {
        this.AuthorisationObject = authorisationObject;
    }
}

