package com.arpak.ikrayourself.ui.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arpak.ikrayourself.data.repo.UserRepository

class SignInFragmentViewModel : ViewModel() {

    private var userRepo = UserRepository()

    private var _isSignIn = MutableLiveData<Boolean>()

    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    init {

        _isSignIn = userRepo.isSignIn
    }

    fun signIn(email: String, password: String) = userRepo.signIn(email,password)
}