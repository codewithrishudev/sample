package com.codewtihrishu.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codewtihrishu.R
import com.codewtihrishu.`interface`.InsertDataInterface
import com.codewtihrishu.adapter.VideoAdapter
import com.codewtihrishu.model.HomeResponse
import com.codewtihrishu.model.ResultsItem
import com.citycarcare.myapplication.viewmodel.homeviewmodel
import kotlinx.android.synthetic.main.videofragmentdata.*

public  class VideoFragment : Fragment() {
    private  lateinit  var videoadapter : VideoAdapter
    private lateinit var mhomeviewmodel: homeviewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mhomeviewmodel=ViewModelProvider(this).get(homeviewmodel :: class.java)


       var  insertDataInterface  = object : InsertDataInterface
       {
           override fun insertdata(trackname: String, trackthumbnail: String, trackurl: String) {
               insertDataToDatabase(trackname,trackthumbnail,trackurl)
           }

       }
        mhomeviewmodel.videolistrepo("Michael+jackson","musicVideo").observe(this, Observer {
            val response=it.response as HomeResponse
            Log.d("fsfsfsffs",""+ response.results!!.size)
            videorecyclerview.layoutManager= GridLayoutManager(activity,2)
            videoadapter= VideoAdapter(activity,response.results,insertDataInterface)
            videorecyclerview.adapter=videoadapter
        })


    }
    private fun insertDataToDatabase(trackname: String, trackthumbnail: String, trackurl: String) {



            val resultsItem = ResultsItem(0, trackname, trackthumbnail, trackurl)
            // Add Data to Database
        mhomeviewmodel.addvideo(resultsItem)
        Toast.makeText(requireContext(), ""+ trackname +" "+"Saved", Toast.LENGTH_LONG).show()





    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.videofragmentdata,container,false)
    }


}