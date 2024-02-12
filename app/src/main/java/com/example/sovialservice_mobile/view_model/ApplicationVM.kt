package com.example.sovialservice_mobile.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sovialservice_mobile.data.ApplicationData

class ApplicationVM: ViewModel() {

    fun setApplication(applicationData: ApplicationData){
        _socOrganization.value = applicationData.socOrg
        _form.value = applicationData.form
        _reason.value = applicationData.reason
        _family.value = applicationData.family
        _living.value = applicationData.living
        _income.value = applicationData.income
    }

    private var _socOrganization = mutableStateOf("")
    var socOrganization: State<String> = _socOrganization

    private var _form = mutableStateOf("")
    var form: State<String> = _form

    private var _reason = mutableStateOf("")
    var reason: State<String> = _reason

    private var _family = mutableStateOf("")
    var family: State<String> = _family

    private var _living = mutableStateOf("")
    var living: State<String> = _living

    private var _income = mutableStateOf("")
    var income: State<String> = _income

    fun setSocOrganization(value: String) {
        _socOrganization.value = value
    }

    fun setForm(value: String) {
        _form.value = value
    }

    fun setReason(value: String) {
        _reason.value = value
    }

    fun setFamily(value: String) {
        _family.value = value
    }

    fun setLiving(value: String) {
        _living.value = value
    }

    fun setIncome(value: String) {
        _income.value = value
    }
}