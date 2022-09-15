package com.rss

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val downloadData = DownloadData(this, xmlListView)
        downloadData.execute("https://trailers.apple.com/trailers/home/rss/newtrailers.rss")
    }

    companion object {
        private class DownloadData(context: Context, listView: ListView) :
            AsyncTask<String?, Void, String>() {
            var propContext: Context = context
            var propListView: ListView = listView
            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                val parseTrailers = ParseTrailers()
                parseTrailers.parse(result)

                val feedAdapter =
                    FeedAdapter(propContext, R.layout.list_record, parseTrailers.trailers)
                propListView.adapter=feedAdapter
            }

            override fun doInBackground(vararg url: String?): String? {
                Log.d(TAG, "it start with ${url[0]}")
                val rssFeed = downloadXMl(url[0])

                if (rssFeed.isNotEmpty())
                    Log.d(TAG, "its ok")
                else
                    Log.e(TAG, "error downlodinf")
                return rssFeed
            }

            private fun downloadXMl(urlPath: String?): String {
                return URL(urlPath).readText()
            }
            // this is a comment
        }
    }
}