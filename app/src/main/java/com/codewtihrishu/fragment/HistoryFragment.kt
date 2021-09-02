package com.codewtihrishu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codewtihrishu.R
import com.codewtihrishu.adapter.HistoryAdapter
import com.citycarcare.myapplication.viewmodel.homeviewmodel
import kotlinx.android.synthetic.main.videofragmentdata.*

class HistoryFragment : Fragment() {
    private  lateinit  var historyadapter : HistoryAdapter
    private lateinit var mhomeviewmodel: homeviewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mhomeviewmodel= ViewModelProvider(this).get(homeviewmodel :: class.java)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val  view :View = LayoutInflater.from(context).inflate(R.layout.videofragmentdata,container,false)
        mhomeviewmodel.readAllData.observe(viewLifecycleOwner, Observer { user ->

            videorecyclerview.layoutManager= GridLayoutManager(activity,2)
            historyadapter= HistoryAdapter(activity,user)
            videorecyclerview.adapter=historyadapter
        })
        return view

    }
}