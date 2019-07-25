package com.mobileapps.isschallengekotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileapps.isschallengekotlin.R
import com.mobileapps.isschallengekotlin.adapters.IssAdapter
import com.mobileapps.isschallengekotlin.models.IssResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainViewInterface  {

    val TAG = "MainActivity"
    lateinit var adapter: IssAdapter
    lateinit var mainPresenter : MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMVP()
        setupViews()
        getResponse()
    }

    private fun getResponse()
    {
        mainPresenter.getResponse()
    }

    private fun setupViews()
    {
        rvIssResponses.layoutManager = LinearLayoutManager(this)
    }

    private fun setupMVP()
    {
        mainPresenter = MainPresenter(this,"33.90","-84.47")
    }


    override fun displayResponse(issResponse: IssResponse?)
    {
        if (issResponse != null) {
            adapter = IssAdapter(issResponse.response)
            rvIssResponses!!.adapter = adapter
        } else {
            Log.d(TAG, "Weather response null")
        }
    }

    override fun displayError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

}
