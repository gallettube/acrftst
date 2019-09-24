package com.tst.apiAC

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        fun serviceCreate(): GetTest {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                //.client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GetTest::class.java)
        }
    }

    lateinit var lista : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val call = serviceCreate().getList()
        doAsync {
            call.enqueue(object : Callback<Array<String>> {
                override fun onFailure(call: Call<Array<String>>?, t: Throwable?) {
                    Log.v("retrofit", "call failed" + t.toString())
                }
                override fun onResponse(call: Call<Array<String>>?, response: Response<Array<String>>?) {
                    lista = response!!.body()
                    val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, lista)
                    ac.setAdapter(adapter)
                }
            })
        }
    }


}
