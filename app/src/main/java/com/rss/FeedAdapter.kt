package com.rss

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ViewHolder(view: View) {
    val tvName: TextView = view.findViewById(R.id.tvName)
    val tvReleaseDate: TextView = view.findViewById(R.id.pubDate)
    val tvSummary: TextView = view.findViewById(R.id.tvSummary)
}

class FeedAdapter(context: Context, val resource: Int, private val trailers: List<FeedEntry>) :
    ArrayAdapter<FeedEntry>(context, resource) {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return trailers.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentTrailer = trailers[position]

        viewHolder.tvName.text = currentTrailer.name
        viewHolder.tvReleaseDate.text = currentTrailer.releaseDate
        viewHolder.tvSummary.text = currentTrailer.summary
        return view
    }
}