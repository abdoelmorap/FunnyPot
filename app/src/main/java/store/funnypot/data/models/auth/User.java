package store.funnypot.data.models.auth;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id"  ) private int id;
    @SerializedName("name"  ) private String name;
    @SerializedName("email"  ) private String email;
    @SerializedName("avatar"  ) private String avatar = null;
    @SerializedName("dob"  ) private String dob = null;
    @SerializedName("phone"  ) private String phone;
    @SerializedName("created_at"  ) private String created_at;
    @SerializedName("updated_at"  ) private String updated_at;
    @SerializedName("confirmed_at"  ) private String confirmed_at;
    @SerializedName("email_verify_token"  )  private String email_verify_token = null;
    @SerializedName("is_vendor"  ) private float is_vendor;
    @SerializedName("vendor_verified_at"  )  private String vendor_verified_at = null;
    @SerializedName("status"  ) private String status;
    @SerializedName("private_notes"  ) private String private_notes = null;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getConfirmed_at() {
        return confirmed_at;
    }

    public String getEmail_verify_token() {
        return email_verify_token;
    }

    public float getIs_vendor() {
        return is_vendor;
    }

    public String getVendor_verified_at() {
        return vendor_verified_at;
    }

    public String getStatus() {
        return status;
    }

    public String getPrivate_notes() {
        return private_notes;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setConfirmed_at(String confirmed_at) {
        this.confirmed_at = confirmed_at;
    }

    public void setEmail_verify_token(String email_verify_token) {
        this.email_verify_token = email_verify_token;
    }

    public void setIs_vendor(float is_vendor) {
        this.is_vendor = is_vendor;
    }

    public void setVendor_verified_at(String vendor_verified_at) {
        this.vendor_verified_at = vendor_verified_at;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrivate_notes(String private_notes) {
        this.private_notes = private_notes;
    }
}
