package com.codewtihrishu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.codewtihrishu.adapter.ViewStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val titles = arrayOf("Videos", "History")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.codewtihrishu.R.layout.activity_main)
        val fm: FragmentManager = supportFragmentManager
        val viewStateAdapter = ViewStateAdapter(fm, lifecycle)
        pager.adapter = viewStateAdapter
        // attaching tab mediator
        TabLayoutMediator(tabLayout, pager) {tab, position ->
            tab.text = titles[position]
        }.attach()

    }
}