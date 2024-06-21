package com.example.noteapp.ui.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp.R
import com.example.noteapp.utils.SharedUtil

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.orange)
        }
        isOnBoardingDone()
    }

    private fun isOnBoardingDone() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val sharedPreferences = SharedUtil()
        sharedPreferences.unit(this)
        if (sharedPreferences.isBoardDone) {
            navController.navigate(R.id.noteFragment)
        } else {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}