package com.example.terasystemhrisv4.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GsonAccountDetails{
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("user")
    @Expose
    var user: AccountDetails? = null
    @SerializedName("userID")
    @Expose
    var userID: String? = null
}