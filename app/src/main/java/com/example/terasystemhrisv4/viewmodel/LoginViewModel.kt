package com.example.terasystemhrisv4.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.terasystemhrisv4.util.isConnected
import com.example.terasystemhrisv4.model.AccountDetails
import com.example.terasystemhrisv4.service.RetrofitFactory
import kotlinx.coroutines.*
import retrofit2.HttpException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var loginError = MutableLiveData<String>()
    var webServiceError = MutableLiveData<String>()
    var accountDetails = MutableLiveData<AccountDetails>()
    private val job = SupervisorJob()
    private val coroutineContext = Dispatchers.IO + job
    var showProgressbar = MutableLiveData<Boolean>()
    val areFieldsEmpty: MediatorLiveData<Boolean> = MediatorLiveData()

    init {
        username.value = ""
        password.value = ""
        showProgressbar.value = false
        areFieldsEmpty.addSource(username) {
            areFieldsEmpty.value = checkForEmptyFields()
        }
        areFieldsEmpty.addSource(password) {
            areFieldsEmpty.value = checkForEmptyFields()
        }
    }

    fun login(){
        if(isConnected(getApplication()))
        {
            showProgressbar.value = true
            val service = RetrofitFactory.makeRetrofitService()
            CoroutineScope(coroutineContext).launch {
                try{
                    val response = service.Login(username.value, password.value)
                    withContext(Dispatchers.Main) {
                        try {
                            if (response.isSuccessful) {
                                val details = response.body()
                                if(details?.status == "0")
                                {
                                    accountDetails.value = details.user
                                }
                                else
                                {
                                    loginError.value = response.body()?.message
                                }
                                showProgressbar.postValue(false)
                            } else {
                                loginError.postValue("Error: ${response.code()}")
                                showProgressbar.postValue(false)
                            }
                        } catch (e: HttpException) {
                            loginError.postValue("Exception ${e.message}")
                            showProgressbar.postValue(false)
                        } catch (e: Throwable) {
                            loginError.postValue(e.message)
                            showProgressbar.postValue(false)
                        }
                    }
                } catch (e: java.net.ConnectException){
                    webServiceError.postValue("Could not connect to the server")
                    showProgressbar.postValue(false)
                } catch (e: java.net.SocketTimeoutException) {
                    webServiceError.postValue("Connection timed out")
                    showProgressbar.postValue(false)
                } catch (e: Exception){
                    webServiceError.postValue(e.message)
                    showProgressbar.postValue(false)
                }
            }
        }
        else
        {
            webServiceError.value = "No Internet Connection"
        }
    }

    private fun checkForEmptyFields() : Boolean {
        if(!username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) return true
        return false
    }

}