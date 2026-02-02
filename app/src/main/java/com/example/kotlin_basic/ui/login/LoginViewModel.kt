package com.example.kotlin_basic.ui.login
import androidx.lifecycle.*
import com.example.kotlin_basic.data.model.LoginRequest
import com.example.kotlin_basic.data.model.LoginResponse
import com.example.kotlin_basic.data.remote.RetrofitClient
import com.example.kotlin_basic.utils.NetworkResult
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginResult: LiveData<NetworkResult<LoginResponse>> = _loginResult
    fun login(request: LoginRequest){
        viewModelScope.launch {
            _loginResult.value = NetworkResult.Loading
            try {
                val response = RetrofitClient.api.login(request)
                if(response.isSuccessful && response.body() != null){
                    _loginResult.value = NetworkResult.Success(response.body()!!)
                }else{
                    _loginResult.value= NetworkResult.Error("Login Failed")
                }
                }catch (e: Exception){
                    _loginResult.value = NetworkResult.Error(e.message.toString())
                }
            }
        }
    }