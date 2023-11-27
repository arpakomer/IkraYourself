package com.arpak.ikrayourself.ui.main.navfragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arpak.ikrayourself.data.model.UserModel
import com.arpak.ikrayourself.data.repo.UserRepository


class ProfileFragmentViewModel: ViewModel() {

    private val userRepo = UserRepository()

    private var _userInfo = MutableLiveData<UserModel>()

    val userInfo: LiveData<UserModel>
        get() = _userInfo


        init {
            getUserInfo()
        }

        private fun getUserInfo() {
            userRepo.getWithUserInfo()
            _userInfo = userRepo.userInfo
        }


    fun signOut() = userRepo.signOut()
}