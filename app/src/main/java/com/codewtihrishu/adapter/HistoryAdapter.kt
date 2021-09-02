package com.codewtihrishu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codewtihrishu.R
import com.codewtihrishu.model.ResultsItem
import com.bumptech.glide.Glide

class HistoryAdapter(activity: Context?,
    results: List<ResultsItem?>,

) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var activity: Context?
    var results: List<ResultsItem?>


    init {
        this.activity = activity
        this.results = results

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_thumbnail = itemView.findViewById<TextView>(R.id.name_thumbnail)
        var image_thumbnail = itemView.findViewById<ImageView>(R.id.image_thumbnail)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.videolistdata,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (results.get(position)?.trackName != null) {
            holder.name_thumbnail.setText(results.get(position)?.trackName)
        }

        if (results.get(position)?.artworkUrl100 != null) {
            Glide.with(activity!!).load(results.get(position)?.artworkUrl100)
                .into(holder.image_thumbnail)
        }

    }

    override fun getItemCount(): Int {
        return results.size
    }
}
