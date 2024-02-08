package com.example.sovialservice_mobile.view

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sovialservice_mobile.data.UserData
import com.example.sovialservice_mobile.ui.theme.Blue
import com.example.sovialservice_mobile.ui.theme.SovialService_mobileTheme
import com.example.sovialservice_mobile.view_model.ApplicationVM
import com.example.sovialservice_mobile.view_model.AuthorisationVM
import com.example.sovialservice_mobile.view_model.MainVM


class MainActivity : ComponentActivity() {
    private var mainVM: MainVM? = null

    enum class StateMain(){
        StateProfile,
        StateHome
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVM = MainVM(UserData("12345678911"))
        setContent {
            SovialService_mobileTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen(){
        var state by remember { mutableStateOf(StateMain.StateHome) }

        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(state){
                StateMain.StateHome -> FragmentHistory()
                StateMain.StateProfile ->
                    if (mainVM?.editProfile!!.value){
                        FragmentProfile()
                    } else{
                        FragmentProfile()
                    }
            }
        }

        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            when(state){
                StateMain.StateHome ->
                    FloatingActionButton(
                        onClick = {
                            mainVM?.setApplicationDialogState(true)
                        },
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(0.dp, 0.dp, 0.dp, 100.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = " "
                        )
                    }
                StateMain.StateProfile ->
                    FloatingActionButton(
                        onClick = {
                            if (mainVM?.editProfile!!.value){
                                mainVM?.setEditProfile(!mainVM?.editProfile!!.value)
                            }else{
                                mainVM?.setEditProfile(!mainVM?.editProfile!!.value)
                            }

                        },
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(0.dp, 0.dp, 0.dp, 100.dp)
                    ) {
                        Icon(
                            when(mainVM?.editProfile!!.value){
                                true -> Icons.Filled.Check
                                false -> Icons.Filled.Edit
                            },
                            contentDescription = " "
                        )
                    }
            }
        }

        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxHeight()) {
            BottomAppBar(
                containerColor = Blue,
                contentColor = Color.White
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { state = StateMain.StateProfile },
                        Modifier.padding(20.dp, 0.dp)
                    ) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            modifier = Modifier.size(50.dp),
                            contentDescription = "Профиль"
                        )
                    }
                    IconButton(
                        onClick = { state = StateMain.StateHome },
                    ) {
                        Icon(
                            Icons.Filled.Home,
                            modifier = Modifier.size(100.dp),
                            contentDescription = "История заявок"
                        )
                    }
                    IconButton(
                        onClick = {
                            mainVM?.setHelpDialogState(true) },
                        Modifier.padding(20.dp, 0.dp)
                    ) {
                        Icon(
                            Icons.Filled.Info,
                            modifier = Modifier.size(50.dp),
                            contentDescription = "Поддержка"
                        )
                    }
                }
            }
        }
        if(mainVM?.helpDialogState?.value == true){
            HelpAlertDialog()
        }
        if(mainVM?.passwordDialogState?.value == true){
            PasswordAlertDialog()
        }
        if(mainVM?.applicationDialogState?.value == true){
            ApplicationAlertDialog()
        }
    }

    @Composable
    fun FragmentHistory(){
        Column(
            Modifier
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.Top,
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 50.dp, 0.dp, 0.dp),
                style = TextStyle(fontSize = 32.sp),
                text = "История заявок"
            )
            LazyColumn(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    item (){
                        Application()
                    }
                })
        }
    }

    @Composable
    fun FragmentProfile() {
        val edit by remember { mutableStateOf(mainVM?.editProfile!!.value) }
        Column(
            Modifier
                .fillMaxHeight(0.9f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 50.dp, 0.dp, 0.dp),
                style = TextStyle(fontSize = 32.sp),
                text = "Профиль"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Основное"
            )
            mainVM?.surname?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = {
                        mainVM?.setSurname(it.filter { char -> char.isLetter() })
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .fillMaxWidth(0.85f),
                    label = {
                        Text(
                            "Фамилия",
                            Modifier
                                .padding(5.dp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    maxLines = 1,
                )
            }
            mainVM?.name?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM?.setName(it.filter { char -> char.isLetter() }) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .fillMaxWidth(0.85f),
                    label = {
                        Text(
                            "Имя",
                            Modifier
                                .padding(5.dp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    maxLines = 1,
                )
            }
            mainVM?.patronymic?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM?.setPatronymic(it.filter { char -> char.isLetter() }) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .fillMaxWidth(0.85f),
                    label = {
                        Text(
                            "Отчество",
                            Modifier
                                .padding(5.dp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    maxLines = 1,
                )
            }

            mainVM?.phone?.let {
                TextField(
                    enabled = edit,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .fillMaxWidth(0.85f),
                    label = {
                        Text(
                            "Телефон",
                            Modifier
                                .padding(5.dp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    value = it.value,
                    onValueChange = {
                        if (it.length <= 11) {
                            mainVM?.setPhone(it.filter { char -> char.isDigit() })
                        }
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        if (mainVM?.phoneState!!.value) {
                            Icon(
                                Icons.Filled.Check,
                                contentDescription = "Коректный ввод"
                            )
                        } else {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Некоректный ввод"
                            )
                        }
                    })
            }
            mainVM?.email?.let {
                TextField(
                    enabled = edit,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .fillMaxWidth(0.85f),
                    label = {
                        Text(
                            "Email",
                            Modifier
                                .padding(5.dp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 24.sp),
                    value = it.value,
                    onValueChange = { mainVM?.setEmail(it) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        if (mainVM?.phoneState!!.value) {
                            Icon(Icons.Filled.Check, contentDescription = "Коректный ввод")
                        } else {
                            Icon(Icons.Filled.Close, contentDescription = "Некоректный ввод")
                        }
                    })
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "Документ"
                )
                TextField(
                    enabled = edit,
                    value = mainVM!!.snils.value,
                    onValueChange = {
                        mainVM!!.setSnils(it.filter { char -> char.isDigit() })
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
                    enabled = edit,
                    value = mainVM!!.document.value,
                    onValueChange = { mainVM!!.setDocument(it.filter { char -> char.isLetter() }) },
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
                    enabled = edit,
                    value = mainVM!!.documentNumber.value,
                    onValueChange = { mainVM!!.setDocumentNumber(it) },
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
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Адрес"
            )
            mainVM?.region?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM!!.setRegion(it.filter { char -> char.isLetter() }) },
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
            }
            mainVM?.regionSmall?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM!!.setRegionSmall(it.filter { char -> char.isLetter() }) },
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
            }
            mainVM?.city?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM!!.setCity(it.filter { char -> char.isLetter() }) },
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
            }
            mainVM?.street?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM!!.setStreet(it.filter { char -> char.isLetter() }) },
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
            }
            mainVM?.home?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM?.setHome(it) },
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
            }
            mainVM?.appartment?.let {
                TextField(
                    enabled = edit,
                    value = it.value,
                    onValueChange = { mainVM?.setAppartment(it.filter { char -> char.isDigit() }) },
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
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { mainVM?.setPasswordDialogState(true) },
                text = "Изменить пароль",
                style = TextStyle(fontSize =  20.sp, color = Color(0xFF2195F2))
            )
            Spacer(modifier = Modifier.height(80.dp))
        }
    }

    @Composable
    fun PasswordAlertDialog(){
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Изменить пароль") },
            text = {
                Column {
                    mainVM?.password?.let {
                        TextField(
                            value = it.value,
                            onValueChange = {
                                mainVM!!.setPassword(it)
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp),
                            label = {
                                Text(
                                    "Текущий пароль",
                                    Modifier
                                        .padding(5.dp)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            textStyle = TextStyle(fontSize = 24.sp),
                            maxLines = 1,
                        )
                    }
                    mainVM?.newPassword?.let {
                        TextField(
                            value = it.value,
                            onValueChange = { mainVM?.setNewPassword(it) },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp),
                            label = {
                                Text(
                                    "Новый пароль",
                                    Modifier
                                        .padding(5.dp)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            textStyle = TextStyle(fontSize = 24.sp),
                            maxLines = 1,
                        )
                    }
                    mainVM?.copyPassword?.let {
                        TextField(
                            value = it.value,
                            onValueChange = { mainVM?.setCopyPassword(it) },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp),
                            label = {
                                Text(
                                    "Повторите новый пароль",
                                    Modifier
                                        .padding(5.dp)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            textStyle = TextStyle(fontSize = 24.sp),
                            maxLines = 1,
                        )
                    }
                } },
            confirmButton = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        Modifier
                            .align(Alignment.CenterVertically)
                            .background(Color(0xFF2195F2), shape = RoundedCornerShape(20.dp))
                            .width(100.dp)
                            .height(40.dp)
                            .clickable { mainVM?.setPasswordDialogState(false) }
                    ) {
                        Text(
                            text = "Готово",
                            Modifier
                                .align(Alignment.Center),
                            style = TextStyle(fontSize = 14.sp),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun ApplicationAlertDialog(){
        val applicationVM = ApplicationVM()
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Подать заявку") },
            text = {
                Column {
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        label = { Text("Социальная организация", Modifier.padding(5.dp)) },
                        textStyle = TextStyle(fontSize = 24.sp),
                        value = applicationVM.socOrganization.value,
                        onValueChange = {applicationVM.setSocOrganization(it)},
                        maxLines = 1,
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        label = { Text("Форма", Modifier.padding(5.dp)) },
                        textStyle = TextStyle(fontSize = 24.sp),
                        value = applicationVM.form.value,
                        onValueChange = {applicationVM.setForm(it)},
                        maxLines = 1,
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        label = { Text("Причина", Modifier.padding(5.dp)) },
                        textStyle = TextStyle(fontSize = 24.sp),
                        value = applicationVM.reason.value,
                        onValueChange = {applicationVM.setReason(it)},
                        maxLines = 1,
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        label = { Text("Состав семьи", Modifier.padding(5.dp)) },
                        textStyle = TextStyle(fontSize = 24.sp),
                        value = applicationVM.family.value,
                        onValueChange = {applicationVM.setFamily(it)},
                        maxLines = 1,
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        label = { Text("Условия проживания", Modifier.padding(5.dp)) },
                        textStyle = TextStyle(fontSize = 24.sp),
                        value = applicationVM.living.value,
                        onValueChange = {applicationVM.setLiving(it)},
                        maxLines = 1,
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        label = { Text("Сумарный доход", Modifier.padding(5.dp)) },
                        textStyle = TextStyle(fontSize = 24.sp),
                        value = applicationVM.income.value,
                        onValueChange = {applicationVM.setIncome(it)},
                        maxLines = 1,
                    )
                }
            },
            confirmButton = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        Modifier
                            .align(Alignment.CenterVertically)
                            .background(Color(0xFF2195F2), shape = RoundedCornerShape(20.dp))
                            .width(100.dp)
                            .height(40.dp)
                            .clickable { mainVM?.setApplicationDialogState(false) }
                    ) {
                        Text(
                            text = "Подать",
                            Modifier
                                .align(Alignment.Center),
                            style = TextStyle(fontSize = 14.sp),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun HelpAlertDialog(){
        AlertDialog(
            onDismissRequest = { mainVM?.setHelpDialogState(false) },
            title = { Text("Тех.поддержка") },
            text = { Text("Если Вы стокнулись с ошибками в работе приложения, пишите на почту:\nsocservice@mail.ru") },
            confirmButton = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        Modifier
                            .align(Alignment.CenterVertically)
                            .background(Color(0xFF2195F2), shape = RoundedCornerShape(20.dp))
                            .width(100.dp)
                            .height(40.dp)
                            .clickable { mainVM?.setHelpDialogState(false) }
                    ) {
                        Text(
                            text = "ОК",
                            Modifier
                                .align(Alignment.Center),
                            style = TextStyle(fontSize = 14.sp),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun Application(){
        Box(
            Modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                .width(350.dp)
                .height(120.dp)
                .background(Color(0xFFE6E6E6), shape = RoundedCornerShape(15.dp))
        ){
            Column(
                Modifier.padding(10.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Заявка",
                    )
                    Text(
                        text = "Одобрено",
                    )
                }
                Text(
                    modifier =  Modifier.padding(0.dp, 10.dp),
                    text = "№346",
                    fontSize = 24.sp
                )
                Text(
                    text = "Дата: 03.06.2023")
            }
        }
    }
}