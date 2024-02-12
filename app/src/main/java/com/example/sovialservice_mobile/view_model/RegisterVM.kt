package com.example.sovialservice_mobile.view_model

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sovialservice_mobile.data.UserData
import com.example.sovialservice_mobile.model.Db
import java.util.Calendar

class RegisterVM(context: Context) : ViewModel() {
    val db = Db(context)

    private var _surname = mutableStateOf("")
    var surname: State<String> = _surname

    fun setSurname(newSurname: String) {
        _surname.value = newSurname
    }

    private var _name = mutableStateOf("")
    var name: State<String> = _name

    fun setName(newName: String) {
        _name.value = newName
    }

    private var _patronymic = mutableStateOf("")
    var patronymic: State<String> = _patronymic

    fun setPatronymic(newPatronymic: String) {
        _patronymic.value = newPatronymic
    }

    private var _birthdate = mutableStateOf("")
    var brithdate: State<String> = _birthdate

    fun setBrithdate(newBrithdate: String) {
        _birthdate.value = newBrithdate
    }

    private var _phone = mutableStateOf("")
    var phone: State<String> = _phone

    private var _phoneState= mutableStateOf(true)
    var phoneState: State<Boolean> = _phoneState

    fun setPhone(newPhone: String) {
        _phone.value = newPhone
    }

    private var _email = mutableStateOf("")
    var email: State<String> = _email

    fun setEmail(newEmail: String){
        _email.value = newEmail
    }

    fun onUserData(): Boolean{
        return true
    }

    private var _snils = mutableStateOf("")
    var snils: State<String> = _snils

    fun setSnils(newSnils: String){
        _snils.value = newSnils
    }

    private var _document = mutableStateOf("Паспорт РФ")
    var document: State<String> = _document

    fun setDocument(newDocument: String){
        _document.value = newDocument
    }

    private var _documentNumber = mutableStateOf("")
    var documentNumber: State<String> = _documentNumber

    fun setDocumentNumber(newDocumentNumber: String){
        _documentNumber.value = newDocumentNumber
    }

    private var _region = mutableStateOf("")
    var region = _region

    fun setRegion(newRegion: String){
        _region.value = newRegion
    }

    private var _regionSmall = mutableStateOf(" ")
    var regionSmall = _regionSmall

    fun setRegionSmall(newRegionSmall: String){
        _regionSmall.value = newRegionSmall
    }

    private var _city = mutableStateOf("")
    var city = _city

    fun setCity(newCity: String){
        _city.value = newCity
    }

    private var _street = mutableStateOf("")
    var street = _street

    fun setStreet(newStreet: String){
        _street.value = newStreet
    }

    private var _home = mutableStateOf("")
    var home = _home

    fun setHome(newHome: String){
        _home.value = newHome
    }

    private var _appartment = mutableStateOf("")
    var appartment = _appartment

    fun setAppartment(newAppartment: String){
        _appartment.value = newAppartment
    }

    private var _password = mutableStateOf("")
    var password = _password

    fun setPassword(newPassword: String){
        _password.value = newPassword
    }

    private var _copyPassword = mutableStateOf("")
    var copyPassword = _copyPassword

    fun setCopyPassword(newCopyPassword: String){
        _copyPassword.value = newCopyPassword
    }

    fun onClickUserData(): Boolean{
        if ((Regex("^(\\+7|8)\\d{10}$").matches(_phone.value)) and (Regex("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})\$").matches(_birthdate.value)) and (Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matches(_email.value)) and (_surname.value != "") and (_name.value != "") and (_patronymic.value != "") ){
            return true
        }
        _dialogState.value = true
        return false
    }

    fun onClickDocumentData(): Boolean{
        if ((Regex("^[0-9]{11}\$").matches(_snils.value)) and (Regex("^[0-9]{10}\$").matches(_documentNumber.value))){
            return true
        }
        _dialogState.value = true
        return false
    }

    fun onClickAddressData(): Boolean{
        if ((_region.value != "") and (_regionSmall.value != "") and (_city.value != "") and (_street.value != "") and (_home.value != "") and (_appartment.value != "")){
            return true
        }
        _dialogState.value = true
        return false
    }

    fun onClickPasswordData(): Boolean{
        if (_password.value == _copyPassword.value){
            return true
        }
        _dialogState.value = true
        return false
    }

    fun onGetSnils(): Boolean{
        if(db.isSnils(_snils.value)){
            return true
        }
        _dialogState.value = true
        return false
    }

    fun onRegisterUser(){
        db.addUser(UserData(
            _snils.value,
            _surname.value,
            _name.value,
            _patronymic.value,
            _birthdate.value,
            _phone.value,
            _email.value,
            _document.value,
            _documentNumber.value,
            _password.value,
            _region.value,
            _regionSmall.value,
            _city.value,
            _street.value,
            _home.value,
            _appartment.value
            ))
    }

    private var _dialogState= mutableStateOf(false)
    var dialogState: State<Boolean> = _dialogState

    fun setDialogState(newDialogState: Boolean) {
        _dialogState.value = newDialogState
    }

}