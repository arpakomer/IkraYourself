package com.arpak.ikrayourself.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.arpak.ikrayourself.R
import com.arpak.ikrayourself.databinding.ActivityDashbordBinding
import com.arpak.ikrayourself.ui.main.bottomfragment.FavoriteFragment
import com.arpak.ikrayourself.ui.main.bottomfragment.HomeFragment
import com.arpak.ikrayourself.ui.main.bottomfragment.NoteFragment
import com.arpak.ikrayourself.ui.main.bottomfragment.SettingFragment
import com.arpak.ikrayourself.ui.main.navfragment.ContactFragment
import com.arpak.ikrayourself.ui.main.navfragment.ProfileFragment
import com.arpak.ikrayourself.ui.main.navfragment.StarRateFragment
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class DashbordActivity : AppCompatActivity(),OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityDashbordBinding

    private val onBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }

    }

    private fun onBackPressedMethod() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashbordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)


        setSupportActionBar(binding.toolbarLayoutBar)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbarLayoutBar,R.string.open_drawer,R.string.close_drawer)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home_category ->openFragment(HomeFragment())
                R.id.favorite_category ->openFragment(FavoriteFragment())
                R.id.note_category ->openFragment(NoteFragment())
                R.id.setting_category ->openFragment(SettingFragment())
            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.nav_profile -> openFragment(ProfileFragment())
            R.id.contact_us -> openFragment(ContactFragment())
            R.id.star_rate -> openFragment(StarRateFragment())
            R.id.share_app ->Toast.makeText(this,"Share App Launching",Toast.LENGTH_SHORT).show()
            R.id.lagout_app ->Toast.makeText(this,"Lagout Launching",Toast.LENGTH_SHORT).show()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun  openFragment(fragment: Fragment){

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navFrameLayoutFragment,fragment)
        fragmentTransaction.commit()

    }

    /*private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment,fragment)
            .commit()
    }*/






}