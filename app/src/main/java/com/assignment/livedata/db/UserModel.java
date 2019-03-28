package com.assignment.livedata.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String username;
    @SerializedName("name")
    private String personName;
    @SerializedName("email")
    private String  emailId;
    private String phone;
    private String website;
    @TypeConverters(AddressConverter.class)
    private Address address;

    public UserModel(String username, String personName, String emailId, String phone, String website, Address address) {
        this.username = username;
        this.personName = personName;
        this.emailId = emailId;
        this.phone = phone;
        this.website = website;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonName() {
        return personName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Address getAddress() {
        return address;
    }
}
