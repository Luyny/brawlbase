package com.luyny.brawlbase.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luyny.brawlbase.ApiResponse
import com.luyny.brawlbase.BrawlStarsApi
import com.luyny.brawlbase.R
import com.luyny.brawlbase.activities.MainActivity.Companion.apiResponse
import com.luyny.brawlbase.activities.MainActivity.Companion.playerTag
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var editTag: EditText
    private lateinit var btnConfirm: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTag = findViewById(R.id.edit_player_tag)
        btnConfirm = findViewById(R.id.btn_confirm_tag)

        btnConfirm.setOnClickListener {
//            playerTag = editTag.text.toString()
            playerTag = "#8L8V20U"
            if (playerTag.isNotBlank()) {
                getCurrentData()
            } else {
                editTag.requestFocus()
                editTag.error = "Tag can't be blank"
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
        val call = service.getPlayerInfo(playerTag)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.code() == 200) {
                    apiResponse = response.body()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        response.errorBody().string(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                print(t.message)
                Toast.makeText(this@LoginActivity, "Request failed.", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}