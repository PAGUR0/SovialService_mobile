package com.example.sovialservice_mobile.view_model

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sovialservice_mobile.data.ApplicationData
import com.example.sovialservice_mobile.data.UserData
import com.example.sovialservice_mobile.model.Db
import java.util.Calendar

class MainVM(context: Context,val snils_: String) : ViewModel() {
    private lateinit var db: Db
    init {
        db = Db(context)
        setUserData(snils_)
    }


    private lateinit var userData: UserData
    fun setUserData(snils: String){
        userData = db.getUser(snils)!!
    }

    val listApplication: MutableList<ApplicationData> = db.getAllApplications(snils_).toMutableList()

    fun addApplication(applicationVM: ApplicationVM): Boolean{
        if ((applicationVM.socOrganization.value != "") and (applicationVM.form.value != "") and (applicationVM.reason.value != "") and (applicationVM.family.value != "")and (applicationVM.living.value != "") and (applicationVM.income.value != "")){
            db.addApplication(
                ApplicationData(
                    listApplication.size + 1,
                    snils_,
                    applicationVM.socOrganization.value,
                    applicationVM.form.value,
                    applicationVM.reason.value,
                    applicationVM.family.value,
                    applicationVM.living.value,
                    applicationVM.income.value,
                    "10.02.2024",
                    "На расмотрение"
                )
            )
            listApplication.add(
                ApplicationData(
                    listApplication.size + 1,
                    snils_,
                    applicationVM.socOrganization.value,
                    applicationVM.form.value,
                    applicationVM.reason.value,
                    applicationVM.family.value,
                    applicationVM.living.value,
                    applicationVM.income.value,
                    "10.02.2024",
                    "На расмотрение"
                )
            )
            return true
        }
        return false
    }

    fun setUserData(){
        userData = db.getUser(snils_)!!
        _snils.value = userData.snils
        _surname.value = userData.surname
        _name.value = userData.name
        _patronymic.value = userData.patronymic
        _birthdate.value = userData.birthday
        _phone.value = userData.phone
        _email.value = userData.email
        _document.value = userData.document
        _documentNumber.value = userData.documentNumber
        _region.value = userData.region
        _regionSmall.value = userData.regionSmall
        _city.value = userData.city
        _street.value = userData.street
        _home.value = userData.home
        _appartment.value = userData.appartment
    }

    fun updateUserData():Boolean{
        if ((_region.value != "") and (_regionSmall.value != "") and (_city.value != "") and (_street.value != "") and (_home.value != "") and (_appartment.value != "") and (Regex("^[0-9]{11}\$").matches(_snils.value)) and (Regex("^[0-9]{10}\$").matches(_documentNumber.value)) and (Regex("^(\\+7|8)\\d{10}$").matches(_phone.value)) and (Regex("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})\$").matches(_birthdate.value)) and (Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matches(_email.value)) and (_surname.value != "") and (_name.value != "") and (_patronymic.value != "")){
            userData = UserData(
                _snils.value,
                _surname.value,
                _name.value,
                _patronymic.value,
                _birthdate.value,
                _phone.value,
                _email.value,
                _document.value,
                _documentNumber.value,
                userData.password,
                _region.value,
                _regionSmall.value,
                _city.value,
                _street.value,
                _home.value,
                _appartment.value
            )
            db.updateUser(userData)
            return true
        }
        return false
    }

    fun updatePassword(): Boolean{
        if((_password.value == userData.password) and (_newPassword.value == _copyPassword.value)){
            db.updatePassword(snils_, newPassword.value)
            return true
        }
        return false
    }

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

    private var _surname = mutableStateOf(userData.surname)
    var surname: State<String> = _surname

    fun setSurname(newSurname: String) {
        _surname.value = newSurname
    }

    private var _name = mutableStateOf(userData.name)
    var name: State<String> = _name

    fun setName(newName: String) {
        _name.value = newName
    }

    private var _patronymic = mutableStateOf(userData.patronymic)
    var patronymic: State<String> = _patronymic

    fun setPatronymic(newPatronymic: String) {
        _patronymic.value = newPatronymic
    }

    private var _birthdate = mutableStateOf(userData.birthday)
    var brithdate: State<String> = _birthdate

    fun setBrithdate(newBrithdate: String) {
        _birthdate.value = newBrithdate
    }

    private var _phone = mutableStateOf(userData.phone)
    var phone: State<String> = _phone

    private var _phoneState= mutableStateOf(true)
    var phoneState: State<Boolean> = _phoneState

    fun setPhone(newPhone: String) {
        _phone.value = newPhone
    }

    private var _email = mutableStateOf(userData.email)
    var email: State<String> = _email

    fun setEmail(newEmail: String){
        _email.value = newEmail
    }

    private var _snils = mutableStateOf(userData.snils)
    var snils: State<String> = _snils

    fun setSnils(newSnils: String){
        _snils.value = newSnils
    }

    private var _document = mutableStateOf(userData.document)
    var document: State<String> = _document

    fun setDocument(newDocument: String){
        _document.value = newDocument
    }

    private var _documentNumber = mutableStateOf(userData.documentNumber)
    var documentNumber: State<String> = _documentNumber

    fun setDocumentNumber(newDocumentNumber: String){
        _documentNumber.value = newDocumentNumber
    }

    private var _region = mutableStateOf(userData.region)
    var region = _region

    fun setRegion(newRegion: String){
        _region.value = newRegion
    }

    private var _regionSmall = mutableStateOf(userData.regionSmall)
    var regionSmall = _regionSmall

    fun setRegionSmall(newRegionSmall: String){
        _regionSmall.value = newRegionSmall
    }

    private var _city = mutableStateOf(userData.city)
    var city = _city

    fun setCity(newCity: String){
        _city.value = newCity
    }

    private var _street = mutableStateOf(userData.street)
    var street = _street

    fun setStreet(newStreet: String){
        _street.value = newStreet
    }

    private var _home = mutableStateOf(userData.home)
    var home = _home

    fun setHome(newHome: String){
        _home.value = newHome
    }

    private var _appartment = mutableStateOf(userData.appartment)
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