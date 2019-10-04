package com.example.terasystemhrisv4.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.terasystemhrisv4.model.AccountDetails


class FileLeaveSuccessViewModel(application: Application) : AndroidViewModel(application) {

    var leaveType = MutableLiveData<String>()
    var wordLeaveType = MutableLiveData<String>()
    var intTimeType = MutableLiveData<Int>()
    var wordTimeType = MutableLiveData<String>()
    var accountDetails = MutableLiveData<AccountDetails>()

    init {

    }

    fun convertLeaveTypeToWord() {
        when (leaveType.value) {
            "1" -> wordLeaveType.value = "Vacation Leave"
            else -> wordLeaveType.value = "Sick Leave"
        }
    }

    fun convertTimeTypeToWord() {
        when (intTimeType.value) {
            1 -> wordTimeType.value = "Whole Day"
            2 -> wordTimeType.value = "Morning"
            else -> wordTimeType.value = "Afternoon"
        }
    }
}