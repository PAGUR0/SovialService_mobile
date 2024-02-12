package com.example.sovialservice_mobile.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sovialservice_mobile.ui.theme.SovialService_mobileTheme
import com.example.sovialservice_mobile.view_model.AuthVM

class AuthActivity : ComponentActivity() {
    private lateinit var authVM: AuthVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authVM = AuthVM(this)
        setContent {
            SovialService_mobileTheme {
                AuthorisationScreen()
            }
        }
    }

    @Preview
    @Composable
    fun AuthorisationScreen() {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 50.dp, 0.dp, 0.dp),
                style = TextStyle(fontSize = 32.sp),
                text = "Социальные услуги"
            )
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
                    label = { Text("СНИЛС", Modifier.padding(5.dp)) },
                    textStyle = TextStyle(fontSize = 24.sp),
                    value = authVM.snils.value,
                    onValueChange = {
                        if(it.length <= 11){
                            authVM.setSnils(it.filter { char -> char.isDigit() })
                        } },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),)
                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
                    label = { Text("Пароль", Modifier.padding(5.dp)) },
                    textStyle = TextStyle(fontSize = 24.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    value = authVM.password.value,
                    onValueChange = {authVM.setPassword(it)},
                    maxLines = 1,

                    trailingIcon = {
                        if(authVM.passwordState.value){
                            Icon(Icons.Filled.Check, contentDescription = "Коректный ввод")
                        } else{
                            Icon(Icons.Filled.Close, contentDescription = "Некоректный ввод")
                        }
                    })
                Box(
                    Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color(0xFF2195F2), shape = RoundedCornerShape(15.dp))
                        .width(120.dp)
                        .height(50.dp)
                        .clickable {
                            if(authVM.onLoginClicked() ){
                                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                                intent.putExtra("snils", authVM.snils.value)
                                startActivity(intent)
                                finish()
                            }
                        }
                ) {
                    Text(
                        text = "Войти",
                        Modifier
                            .align(Alignment.Center),
                        style = TextStyle(fontSize = 20.sp),
                        color = Color(0xFFFFFFFF)
                    )
                }
            }
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(fontSize = 20.sp),
                    text = "У Вас нет аккаунта?"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(15.dp)
                        .clickable {
                            startActivity(Intent(this@AuthActivity, RegisterActivity::class.java))
                        },
                    style = TextStyle(fontSize = 24.sp),
                    text = "Зарегистрироваться"
                )
            }
        }
    }
}
