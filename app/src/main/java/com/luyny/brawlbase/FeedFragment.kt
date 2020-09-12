package com.luyny.brawlbase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var apiResponse: ApiResponse
    private lateinit var btnConfirm: Button
    private lateinit var editPlayerTag: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        btnConfirm = view.findViewById(R.id.btn_confirm_token)
        editPlayerTag = view.findViewById(R.id.edit_player_tag)
        recyclerView = view.findViewById(R.id.my_recycler_view)

        btnConfirm.setOnClickListener {
            it.isClickable = false
            getCurrentData()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getCurrentData() {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer " + getString(R.string.token))
                .build()
            return@addInterceptor chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.baseUrl))
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BrawlStarsApi::class.java)
        val call = service.getPlayerInfo(editPlayerTag.text.toString())

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.code() == 200) {
                    apiResponse = response.body()
                    viewManager = GridLayoutManager(context, 3,LinearLayoutManager.VERTICAL,false)
                    viewAdapter = MyAdapter(apiResponse.brawlers!!)
                    recyclerView.apply {
                        // use this setting to improve performance if you know that changes
                        // in content do not change the layout size of the RecyclerView
                        setHasFixedSize(true)
                        // use a linear layout manager
                        layoutManager = viewManager
                        // specify an viewAdapter (see also next example)
                        adapter = viewAdapter
                    }
                } else {
                    Toast.makeText(
                        this@FeedFragment.context,
                        response.errorBody().string(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                print(t.message)
                Toast.makeText(this@FeedFragment.context, "Request failed.", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}