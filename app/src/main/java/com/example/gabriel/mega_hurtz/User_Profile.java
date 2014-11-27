package com.example.gabriel.mega_hurtz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Devin on 11/26/2014.
 */
public class User_Profile implements Parcelable {


    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getUser_id() {
        return user_id;
    }

    private String username, firstname, lastname, email, phone, dob, user_id;


    public User_Profile(String username1, String firstname, String lastname, String email, String phone, String dob, String user_id) {
        this.username = username1;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.user_id = user_id;

    }

    //Clears userProfile
    public void clear() {
        username = "N/A";
        firstname = "N/A";
        lastname = "N/A";
        email = "N/A";
        phone = "N/A";
        dob = "N/A";
        user_id = "N/A";

    }

    //Setups method to implement Parcel in order to send information to other class
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(username);
        out.writeString(firstname);
        out.writeString(lastname);
        out.writeString(email);
        out.writeString(phone);
        out.writeString(dob);
        out.writeString(user_id);

    }

    //Creator needed for Parcels
    public static final Parcelable.Creator<User_Profile> CREATOR
            = new Parcelable.Creator<User_Profile>() {
        public User_Profile createFromParcel(Parcel in) {
            return new User_Profile(in);
        }

        public User_Profile[] newArray(int size) {
            return new User_Profile[size];
        }
    };

    //Create class again with Parcel
    private User_Profile(Parcel in) {
        this.username = in.readString();
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.dob = in.readString();
        this.user_id = in.readString();

    }
}