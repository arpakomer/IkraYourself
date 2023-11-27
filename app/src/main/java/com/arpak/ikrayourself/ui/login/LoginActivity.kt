package com.arpak.ikrayourself.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.arpak.ikrayourself.MainActivity
import com.arpak.ikrayourself.R
import com.arpak.ikrayourself.databinding.ActivityLoginBinding
import com.arpak.ikrayourself.ui.main.DashbordActivity
import com.arpak.ikrayourself.util.Constants.SIGN_IN
import com.arpak.ikrayourself.util.Constants.SIGN_UP
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
//    private val loginViewModel by viewModels<LoginActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)

//        loginViewModel.login()
//        installSplashScreen().apply {
//
//            setKeepOnScreenCondition{
//                loginViewModel.isLoading.value
//            }
//
//        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        Firebase.auth.currentUser?.let {
            val intent = Intent(this, DashbordActivity::class.java)
            startActivity(intent)
            finish()
        }

        val titleListSign = arrayListOf(SIGN_IN, SIGN_UP)

        binding.viewPagerLoginActivity.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.loginTabLayout, binding.viewPagerLoginActivity) { tab, position ->
            tab.text = titleListSign[position]
        }.attach()
    }


}

