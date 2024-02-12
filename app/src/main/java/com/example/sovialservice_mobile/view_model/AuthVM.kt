package com.example.sovialservice_mobile.view_model

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sovialservice_mobile.model.Db

class AuthVM(context: Context) : ViewModel() {
    val db = Db(context)

    private var _snils= mutableStateOf("")
    var snils: State<String> = _snils

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
        val login = db.authUser(_snils.value, _password.value)
        _passwordState.value = login
        return login
    }
}