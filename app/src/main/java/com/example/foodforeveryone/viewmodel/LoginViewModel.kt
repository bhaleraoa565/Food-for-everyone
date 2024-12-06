package com.example.foodforeveryone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodforeveryone.model.LoginRequest
import com.example.foodforeveryone.model.LoginResponse
import com.example.foodforeveryone.model.RetrofitClient
import retrofit2.Call
import retrofit2.Response


class LoginViewModel : ViewModel() {


    private val _selectedTabIndex = MutableLiveData<Int>()
    val selectedTabIndex: LiveData<Int> get() = _selectedTabIndex

    fun updateSelectedTab(index: Int) {
        _selectedTabIndex.value = index
    }

    private val correctEmail = "Abhi@gmail.com"
    private val correctPassword = "123456"

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    fun validateCredentials(email: String, password: String) {
        if (email == correctEmail && password == correctPassword) {
            _loginState.value = LoginState.Success
        } else {
            _loginState.value = LoginState.Error("Invalid Email or Password")
        }
    }

    sealed class LoginState {
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }

}

//    private val _loginState = MutableLiveData<LoginState>()
//    val loginState: LiveData<LoginState> get() = _loginState
//
//    fun loginUser(email: String, password: String) {
//        val loginRequest = LoginRequest(email, password)
//
//        RetrofitClient.instance.login(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    _loginState.value = LoginState.Success(response.body()?.token ?: "")
//                } else {
//                    _loginState.value = LoginState.Error("Invalid credentials")
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _loginState.value = LoginState.Error("Network Error: ${t.message}")
//            }
//        })
//    }
//
//    fun updateSelectedTab(position: Int) {
//
//    }
//
//    sealed class LoginState {
//        data class Success(val token: String) : LoginState()
//        data class Error(val message: String) : LoginState()
//    }
//}









