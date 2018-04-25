package com.saddam.nur.kalimasadabaru.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUserLoginModel {
    @SerializedName("user")
    @Expose
    private List<user> user = null;
    @SerializedName("success")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<user> getUser() {
        return user;
    }

    public void setUser(List<user> user) {
        this.user = user;
    }

    public Integer getSuccess() {
        return status;
    }

    public void getSuccess(Integer success) {
        this.status = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
