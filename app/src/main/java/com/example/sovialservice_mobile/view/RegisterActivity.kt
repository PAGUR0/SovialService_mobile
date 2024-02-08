package com.example.sovialservice_mobile.view

import android.os.Bundle
import android.widget.DatePicker
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sovialservice_mobile.ui.theme.SovialService_mobileTheme
import com.example.sovialservice_mobile.view_model.RegisterVM
import java.util.Calendar

class RegisterActivity : ComponentActivity() {
    private val registerVM = RegisterVM()

    enum class StateRegister{
        StateUserData,
        StateDocumentData,
        StateAddressData,
        StatePasswordData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SovialService_mobileTheme {
                RegisterScreen()
            }
        }
    }

    @Composable
    fun RegisterScreen() {
        var state by remember { mutableStateOf(StateRegister.StateUserData) }
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(fontSize = 32.sp),
                text = when(state){
                    StateRegister.StateUserData -> "Основное"
                    StateRegister.StateDocumentData -> "Документы"
                    StateRegister.StateAddressData -> "Адрес"
                    StateRegister.StatePasswordData -> "Пароль"
                }
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                when(state){
                    StateRegister.StateUserData -> FragmentUserData()
                    StateRegister.StateDocumentData -> FragmentDocumentData()
                    StateRegister.StateAddressData -> FragmentAddressData()
                    StateRegister.StatePasswordData -> FragmentPasswordData()
                }
                Spacer(
                    Modifier
                        .height(20.dp)
                )
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.8f),
                    horizontalArrangement =
                    when(state){
                        StateRegister.StateUserData -> Arrangement.Center
                        else -> { Arrangement.SpaceBetween }
                    }

                ) {
                    when(state){
                        StateRegister.StateUserData -> Box(Modifier.height(50.dp))
                        else -> {
                            Box(
                                Modifier
                                    .align(Alignment.CenterVertically)
                                    .background(
                                        Color(0xFFE6DFEB),
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .width(125.dp)
                                    .height(50.dp)
                                    .clickable {
                                        when (state) {
                                            StateRegister.StateDocumentData -> state =
                                                StateRegister.StateUserData

                                            StateRegister.StateAddressData -> state =
                                                StateRegister.StateDocumentData

                                            StateRegister.StatePasswordData -> state =
                                                StateRegister.StateAddressData

                                            else -> {}
                                        }
                                    }
                            ) {
                                Text(
                                    text = "Назад",
                                    Modifier
                                        .align(Alignment.Center),
                                    style = TextStyle(fontSize = 20.sp)
                                )
                            }
                        }
                    }
                    Box(
                        Modifier
                            .align(Alignment.CenterVertically)
                            .background(Color(0xFF2195F2), shape = RoundedCornerShape(15.dp))
                            .width(150.dp)
                            .height(50.dp)
                            .clickable {
                                when (state) {
                                    StateRegister.StateUserData -> {
                                        if (registerVM.onUserData()) {
                                            state = StateRegister.StateDocumentData
                                        }
                                    }

                                    StateRegister.StateDocumentData -> state =
                                        StateRegister.StateAddressData

                                    StateRegister.StateAddressData -> state =
                                        StateRegister.StatePasswordData

                                    else -> {}
                                }
                            }
                    ) {
                        Text(
                            text =
                            when(state){
                                StateRegister.StatePasswordData -> "Регистрация"
                                else -> {"Далее"}
                            },
                            Modifier
                                .align(Alignment.Center),
                            style = TextStyle(fontSize = 20.sp),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }

            Column(Modifier.fillMaxWidth()){
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(fontSize = 20.sp),
                    text = "У Вас уже есть аккаунт?"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(15.dp)
                        .clickable {
                            finish()
                        },
                    style = TextStyle(fontSize = 24.sp),
                    text = "Войти"
                )
            }
        }
    }

    @Composable
    fun FragmentUserData(){
        var isDatePickerOpen by remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = registerVM.surname.value,
                onValueChange = {
                    registerVM.setSurname(it.filter { char -> char.isLetter() })
                                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Фамилия",
                        Modifier
                            .padding(5.dp))
                        },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
                )
            TextField(
                value = registerVM.name.value,
                onValueChange = {registerVM.setName(it.filter { char -> char.isLetter() })},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Имя",
                        Modifier
                            .padding(5.dp)
                    ) },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.patronymic.value,
                onValueChange = {registerVM.setPatronymic(it.filter { char -> char.isLetter() })},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Отчество",
                        Modifier
                            .padding(5.dp)
                    ) },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )

            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Телефон",
                        Modifier
                            .padding(5.dp)
                    ) },
                textStyle = TextStyle(fontSize = 24.sp),
                value = registerVM.phone.value,
                onValueChange = {
                    if(it.length <= 11){
                        registerVM.setPhone(it.filter { char -> char.isDigit() })
                    } },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    if(registerVM.phoneState.value){
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "Коректный ввод"
                        )
                    } else{
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Некоректный ввод"
                        )
                    }
                })
            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Email",
                        Modifier
                            .padding(5.dp)
                    ) },
                textStyle = TextStyle(fontSize = 24.sp),
                value = registerVM.email.value,
                onValueChange = { registerVM.setEmail(it) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                trailingIcon = {
                    if(registerVM.phoneState.value){
                        Icon(Icons.Filled.Check, contentDescription = "Коректный ввод")
                    } else{
                        Icon(Icons.Filled.Close, contentDescription = "Некоректный ввод")
                    }
                })
        }
    }

    @Composable
    fun FragmentAddressData() {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = registerVM.region.value,
                onValueChange = { registerVM.setRegion(it.filter { char -> char.isLetter() }) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Регион",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.regionSmall.value,
                onValueChange = { registerVM.setRegionSmall(it.filter { char -> char.isLetter() }) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Район",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.city.value,
                onValueChange = { registerVM.setCity(it.filter { char -> char.isLetter() }) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Город",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.street.value,
                onValueChange = { registerVM.setStreet(it.filter { char -> char.isLetter() }) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Улица",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.home.value,
                onValueChange = { registerVM.setHome(it) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Дом",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.appartment.value,
                onValueChange = { registerVM.setAppartment(it.filter { char -> char.isDigit() }) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Квартира",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
        }
    }

    @Composable
    fun FragmentDocumentData() {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = registerVM.snils.value,
                onValueChange = {
                    registerVM.setSnils(it.filter { char -> char.isDigit() })
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "СНИЛС",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.document.value,
                onValueChange = { registerVM.setDocument(it.filter { char -> char.isLetter() }) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Документ",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.documentNumber.value,
                onValueChange = { registerVM.setDocumentNumber(it) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Номер документа",
                        Modifier
                            .padding(5.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
        }
    }

    @Composable
    fun FragmentPasswordData() {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = registerVM.password.value,
                onValueChange = {
                    registerVM.setPassword(it)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Пароль",
                        Modifier
                            .padding(5.dp)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
            TextField(
                value = registerVM.copyPassword.value,
                onValueChange = { registerVM.setCopyPassword(it) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(0.85f),
                label = {
                    Text(
                        "Повторить пароль",
                        Modifier
                            .padding(5.dp)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(fontSize = 24.sp),
                maxLines = 1,
            )
        }
    }
}