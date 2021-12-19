package com.noob.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.noob.coroutineretrofit.R
import com.noob.data.api.ApiHelper
import com.noob.data.api.RetrofitBuilder
import com.noob.data.utils.Status
import com.noob.data.view_model.MainViewModel

private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { users ->
                            Log.e(TAG,"Code : ${it.message}")
                            Log.e(TAG,"Data Size : ${users.size}")
                        }
                    }
                    Status.ERROR -> {
                        Log.e(TAG,"Error : ${it.message}")

                    }
                    Status.LOADING -> {
                        Log.e(TAG,"Loading : ${it.status}")
                    }
                }
            }
        })
    }
}