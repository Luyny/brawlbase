package com.luyny.brawlbase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luyny.brawlbase.R

class BrawlerDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_brawler_details, container, false)

        //TODO this fragment will be shown when a brawler is clicked

        return view
    }

}