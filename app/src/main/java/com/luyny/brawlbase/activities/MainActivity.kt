package com.luyny.brawlbase.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luyny.brawlbase.ApiResponse
import com.luyny.brawlbase.R
import com.luyny.brawlbase.fragments.FeedFragment

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var apiResponse: ApiResponse
        lateinit var playerTag: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateFragment(R.id.frame_main, FeedFragment())
    }

    private fun inflateFragment(fragment: Int, fragmentClass: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragment, fragmentClass)
        fragmentTransaction.commit()
    }


}