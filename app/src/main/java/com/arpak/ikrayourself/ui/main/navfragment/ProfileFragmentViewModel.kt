package com.arpak.ikrayourself.ui.main.navfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arpak.ikrayourself.data.model.UserModel
import com.arpak.ikrayourself.data.repo.UserRepository

class ProfileFragmentViewModel {

    private val usersRepo = UserRepository()

    private var _userInfo = MutableLiveData<UserModel>()

    val userInfo: LiveData<UserModel>
        get() = _userInfo

    init {
        getUserInformation()
    }

    private fun getUserInformation() {
        usersRepo.getUserInfo()
        _userInfo = usersRepo.userInfo
    }

    fun signOut() = usersRepo.signOut()

}