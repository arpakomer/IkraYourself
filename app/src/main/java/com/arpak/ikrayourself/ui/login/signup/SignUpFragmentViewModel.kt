package com.arpak.ikrayourself.ui.login.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arpak.ikrayourself.data.repo.UserRepository

class SignUpFragmentViewModel : ViewModel() {

    private var userRepo = UserRepository()

    private var _isInfoValid = MutableLiveData<Boolean>()
    val isInfoValid: LiveData<Boolean> = _isInfoValid

    private var _isValidMail = MutableLiveData<Boolean>()
    val isValidMail: LiveData<Boolean> = _isValidMail

    private var _isPasswordMatch = MutableLiveData<Boolean>()
    val isPasswordMatch: LiveData<Boolean> = _isPasswordMatch

    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: LiveData<Boolean> = _isSignUp


    init {
        _isSignUp = userRepo.isSignUp
    }


    fun signUp(email: String,
               password: String,
               confirmPassword: String,
               phoneNumber: String
    ) {

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            _isInfoValid.value = false
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
                _isValidMail.value = false
            }else{
                if (password != confirmPassword){
                    _isPasswordMatch.value = false
                }else{
                    userRepo.sigUp(email,password,phoneNumber)
                }
            }
        }
    }
}