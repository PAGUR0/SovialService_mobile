package com.example.sovialservice_mobile.view_model

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sovialservice_mobile.data.UserData
import java.util.Calendar

class MainVM(val userData: UserData) : ViewModel() {
    private var _helpDialogState= mutableStateOf(false)
    var helpDialogState: State<Boolean> = _helpDialogState

    fun setHelpDialogState(newHelpDialogState: Boolean) {
        _helpDialogState.value = newHelpDialogState
    }

    private var _passwordDialogState= mutableStateOf(false)
    var passwordDialogState: State<Boolean> = _passwordDialogState

    fun setPasswordDialogState(newPasswordDialogState: Boolean) {
        _passwordDialogState.value = newPasswordDialogState
    }

    private var _applicationDialogState = mutableStateOf(false)
    var applicationDialogState: State<Boolean> = _applicationDialogState

    fun setApplicationDialogState(newApplicationDialogState: Boolean) {
        _applicationDialogState.value = newApplicationDialogState
    }

    private var _editProfile= mutableStateOf(false)
    val editProfile: State<Boolean> = _editProfile

    fun setEditProfile(newEditProfile: Boolean) {
        _editProfile.value = newEditProfile
    }

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

    private var _birthdate = mutableStateOf(Calendar.getInstance())
    var brithdate: State<Calendar> = _birthdate

    fun setBrithdate(newBrithdate: Calendar) {
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

    private var _snils = mutableStateOf("")
    var snils: State<String> = _snils

    fun setSnils(newSnils: String){
        _snils.value = newSnils
    }

    private var _document = mutableStateOf("")
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

    private var _regionSmall = mutableStateOf("")
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
    var password: State<String> = _password

    fun setPassword(newPassword: String){
        _password.value = newPassword
    }

    private var _newPassword = mutableStateOf("")
    var newPassword: State<String> = _newPassword

    fun setNewPassword(newNewPassword: String){
        _newPassword.value = newNewPassword
    }

    private var _copyPassword = mutableStateOf("")
    var copyPassword: State<String> = _copyPassword

    fun setCopyPassword(newCopyPassword: String){
        _copyPassword.value = newCopyPassword
    }
}