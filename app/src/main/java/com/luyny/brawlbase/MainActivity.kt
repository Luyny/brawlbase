package com.luyny.brawlbase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

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