package com.arpak.ikrayourself.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arpak.ikrayourself.ui.login.signin.SignInFragment
import com.arpak.ikrayourself.ui.login.signup.SignupFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> SignInFragment()
        1 -> SignupFragment()
        else -> SignInFragment()
    }

    override fun getItemCount(): Int = 2


}