package com.luyny.brawlbase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.luyny.brawlbase.Brawler
import com.luyny.brawlbase.R
import com.squareup.picasso.Picasso

class BrawlerDetailsFragment(private val brawler: Brawler) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_brawler_details, container, false)
        val imgBrawler: ImageView = view.findViewById(R.id.img_brawler_portrait)
        val textBrawlerName: TextView = view.findViewById(R.id.text_brawler_name)
        val textBrawlerTrophies: TextView = view.findViewById(R.id.text_brawler_trophies)
        val textBrawlerHighestTrophies: TextView = view.findViewById(R.id.text_brawler_highest_trophies)
        val textBrawlerRank: TextView = view.findViewById(R.id.text_brawler_rank)
        val textBrawlerPower: TextView = view.findViewById(R.id.text_brawler_power)
        val btnBack: Button = view.findViewById(R.id.btn_back)

        val imgId=context!!.resources.getIdentifier("b${brawler.id}", "drawable", context!!.packageName)

        Picasso.get().load(imgId).into(imgBrawler)
        textBrawlerName.text = brawler.name
        textBrawlerTrophies.text = brawler.trophies.toString()
        textBrawlerPower.text = brawler.power.toString()
        textBrawlerRank.text = brawler.rank.toString()
        textBrawlerHighestTrophies.text = brawler.highestTrophies.toString()

        btnBack.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_main, FeedFragment())
                .commit()
        }



        return view
    }

}