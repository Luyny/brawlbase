package com.luyny.brawlbase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    companion object {
        var playerTag: String = "#8L8V20U"
        var apiResponse: ApiResponse? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateFragment(R.id.frame_main, FeedFragment())
    }

    private fun inflateFragment(fragment: Int, fragmentClass: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(fragment, fragmentClass)
        fragmentTransaction.commit()
    }


}