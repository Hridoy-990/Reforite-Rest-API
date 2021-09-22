package com.example.reforiterestapidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("My debugger", "onCreate: called")
        var rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var api = rf.create(RetrofitInterface::class.java)
        var call:Call<List<PostModel?>?>? = api.posts

        call?.enqueue(object : Callback<List<PostModel?>?>{

            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {
                var postList : List <PostModel>? = response.body() as List<PostModel>
                var post : ArrayList<PostModel> = arrayListOf()

                postList?.let {
                    Log.d("My debugger", "onResponse: postList is not empty")
                    for(i in it.indices)
                    {
                        post.add(postList[i])
                        Log.d("My debugger", "onResponse: {$postList[i]}")
                    }
                }

                Log.d("My debugger", "onResponse: called"+postList?.size)
                Log.d(TAG, "onResponse: ")




                adapter = RecyclerAdapter()
                adapter.submitList(post)
                recycler_view.adapter = adapter

            }
            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
            }

        })
    }
    
    companion object{
        private const val TAG = "MainActivity"
    }
}