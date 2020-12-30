package ru.quintsoft.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _hasValidEmail= MutableLiveData<Boolean>()
    val hasValidEmail: LiveData<Boolean>
        get() = _hasValidEmail

    private val _hasValidPassword= MutableLiveData<Boolean>()
    val hasValidPassword: LiveData<Boolean>
        get() = _hasValidPassword

    private val _hasInfo = MutableLiveData<Boolean>()
    val hasInfo: LiveData<Boolean>
        get() = _hasInfo

    fun testEmailValid(email: String) {
        _hasValidEmail.value = !email.isBlank()
    }

    fun testPasswordValid(password: String) {
        _hasValidPassword.value = !password.isBlank()
    }

    fun checkPass() {
        _hasInfo.value = validate()
    }

    fun resetValues(bTorF: Boolean) {
        _hasInfo.value = bTorF
        _hasValidEmail.value = bTorF
        _hasValidPassword.value = bTorF
    }

    private fun validate(): Boolean {
        return true
    }
}