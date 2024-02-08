package com.example.sovialservice_mobile.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthorisationVM : ViewModel() {
    private var _snils= mutableStateOf("")
    var snils: State<String> = _snils
    private var _snilsState= mutableStateOf(true)
    var snilsState: State<Boolean> = _snilsState

    private var _password = mutableStateOf("")
    var password: State<String> = _password
    private var _passwordState= mutableStateOf(true)
    var passwordState: State<Boolean> = _passwordState

    fun setSnils(newSnils: String) {
        _snils.value = newSnils
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClicked(): Boolean {
        _snilsState.value = snils.value == "12345678911"
        _passwordState.value = password.value == "1234"
        return (_snilsState.value) and (_passwordState.value)
    }

}