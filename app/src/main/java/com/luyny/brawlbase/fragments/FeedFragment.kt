package com.luyny.brawlbase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luyny.brawlbase.ApiResponse
import com.luyny.brawlbase.BrawlStarsApi
import com.luyny.brawlbase.MyAdapter
import com.luyny.brawlbase.R
import com.luyny.brawlbase.activities.MainActivity
import com.luyny.brawlbase.activities.MainActivity.Companion.apiResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var textBrawlerName: TextView
    private lateinit var textBrawlerTrophies: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        recyclerView = view.findViewById(R.id.my_recycler_view)
        textBrawlerName = view.findViewById(R.id.text_player_name)
        textBrawlerTrophies = view.findViewById(R.id.text_player_trophies)

        val viewManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        val viewAdapter = MyAdapter(apiResponse.brawlers!!,activity!!.supportFragmentManager)

        textBrawlerName.text = apiResponse.name
        textBrawlerTrophies.text = apiResponse.trophies.toString()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return view
    }
}