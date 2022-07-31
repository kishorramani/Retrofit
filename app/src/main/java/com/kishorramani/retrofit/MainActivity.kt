package com.kishorramani.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kishorramani.retrofit.api.RetrofitInstance
import com.kishorramani.retrofit.model.Post
import com.kishorramani.retrofit.repository.Repository
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val TAG = "MainActivity"

    private lateinit var txtData: TextView
    private lateinit var recyclerview: RecyclerView

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        setupRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        //https://jsonplaceholder.typicode.com/posts/1
        /*viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")
                Log.d(TAG, "Response: ${response.body()?.userId.toString()}")
                Log.d(TAG, "onCreate: ${response.body()?.id.toString()}")
                Log.d(TAG, "onCreate: ${response.body()?.title}")
                Log.d(TAG, "onCreate: ${response.body()?.body}")
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/

        //https://jsonplaceholder.typicode.com/posts/15
        /*viewModel.getPost2(15)
        viewModel.myResponse2.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response1: ${response.body()?.toString()}")
                Log.d(TAG, "Response1: ${response.body()?.userId.toString()}")
                Log.d(TAG, "onCreate1: ${response.body()?.id.toString()}")
                Log.d(TAG, "onCreate1: ${response.body()?.title}")
                Log.d(TAG, "onCreate1: ${response.body()?.body}")
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/

        //https://jsonplaceholder.typicode.com/posts?userId=7
        /*viewModel.getCustomPost(3)
        viewModel.myCustomPost.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")
                response.body()?.forEach {
                    Log.d(TAG, "Response2: ${it.userId.toString()}")
                    Log.d(TAG, "Response2: ${it.id.toString()}")
                    Log.d(TAG, "Response2: ${it.title}")
                    Log.d(TAG, "Response2: ${it.body}")
                    Log.d(TAG, "Response2: ---------------------------------")
                }
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/

        //https://jsonplaceholder.typicode.com/posts?userId=7&_sort=id&_order=desc
        /*viewModel.getCustomPost2(3, "id", "desc")
        viewModel.myCustomPost2.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")

                response.body()?.let {
                    myAdapter.setData(it)
                }

                response.body()?.forEach {
                    Log.d(TAG, "Response: ${it.userId.toString()}")
                    Log.d(TAG, "Response: ${it.id.toString()}")
                    Log.d(TAG, "Response: ${it.title}")
                    Log.d(TAG, "Response: ${it.body}")
                    Log.d(TAG, "Response: ---------------------------------")
                }
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/

        //call getCustomPost2 api without mvvvm
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getCustomPost2(3, "id", "desc")
            } catch (e: IOException) {
                Log.e(com.kishorramani.retrofit2.TAG, "IOException, You might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(com.kishorramani.retrofit2.TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                myAdapter.setData(response.body()!!)
            } else {
                Log.e(com.kishorramani.retrofit2.TAG, "Response not successful")
            }
        }

        //https://jsonplaceholder.typicode.com/posts?userId=7&_sort=id&_order=desc
        //link as above request but here we add params using hashmap
        /*val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"
        viewModel.getCustomPost3(3, options)
        viewModel.myCustomPost3.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")
                response.body()?.let {
                    myAdapter.setData(it)
                }

                response.body()?.forEach {
                    Log.d(TAG, "Response: ${it.userId.toString()}")
                    Log.d(TAG, "Response: ${it.id.toString()}")
                    Log.d(TAG, "Response: ${it.title}")
                    Log.d(TAG, "Response: ${it.body}")
                    Log.d(TAG, "Response: ---------------------------------")
                }
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/


        //https://jsonplaceholder.typicode.com/posts
        //body - Post(userId=2, id=5, title=Title, body=Description)
        /*val myPost = Post(2, 5, "Title", "Description")
        viewModel.pushPost(myPost)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d(TAG, "onCreate: ${response.body().toString()}")
                Log.d(TAG, "onCreate: ${response.code()}")      //Get 201 code for created
                Log.d(TAG, "onCreate: ${response.message()}")
            } else {
                Log.d(TAG, "onCreate: ${response.errorBody()}")
            }
        })*/


        //https://jsonplaceholder.typicode.com/posts
        //body - Post(userId=2, id=5, title=Title, body=Description)
        /*viewModel.pushPost2(2, 2, "Kishor", "Android Application Developer")
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d(TAG, "onCreate: ${response.body().toString()}")
                Log.d(TAG, "onCreate: ${response.code()}")      //Get 201 code for created
                Log.d(TAG, "onCreate: ${response.message()}")
            } else {
                Log.d(TAG, "onCreate: ${response.errorBody()}")
            }
        })*/

        //https://jsonplaceholder.typicode.com/posts/1
        /*viewModel.getPost1()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")
                Log.d(TAG, "userId: ${response.body()?.userId.toString()}")
                Log.d(TAG, "id: ${response.body()?.id.toString()}")
                Log.d(TAG, "title: ${response.body()?.title}")
                Log.d(TAG, "body: ${response.body()?.body}")
                Log.d(TAG, "Headers: ${response.headers().toString()}")
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/

        //here, header is different; see simpleApi.kt
        //@Headers(
        //        "Authorization: 123123123",
        //        "Platform: Android"
        //    )
        /*viewModel.getPost2()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")
                Log.d(TAG, "userId: ${response.body()?.userId.toString()}")
                Log.d(TAG, "id: ${response.body()?.id.toString()}")
                Log.d(TAG, "title: ${response.body()?.title}")
                Log.d(TAG, "body: ${response.body()?.body}")
                Log.d(TAG, "Headers: ${response.headers().toString()}")
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/

        //add header dynamically - auth: 11112222
       /* viewModel.getPost3("11112222")
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e(TAG, "Response: ${response.body()?.toString()}")
                Log.d(TAG, "userId: ${response.body()?.userId.toString()}")
                Log.d(TAG, "id: ${response.body()?.id.toString()}")
                Log.d(TAG, "title: ${response.body()?.title}")
                Log.d(TAG, "body: ${response.body()?.body}")
                Log.d(TAG, "Headers: ${response.headers()}")
                txtData.text = response.body()?.toString()
            } else {
                Log.e(TAG, "onCreate: ${response.errorBody().toString()}")
                txtData.text = response.code().toString()
            }
        })*/
    }

    private fun init() {
        txtData = findViewById(R.id.txtData)
        recyclerview = findViewById(R.id.recyclerView)
    }

    private fun setupRecyclerview() {
        recyclerview.adapter = myAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }
}