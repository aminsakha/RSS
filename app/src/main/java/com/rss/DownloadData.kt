package com.rss

import android.os.AsyncTask
import android.util.Log
import java.net.URL

const val TAG = "MyActivity"


//class DownloadData : AsyncTask<String?, Void, String>() {
//    override fun onPostExecute(result: String) {
//        super.onPostExecute(result)
//        val parseTrailers = ParseTrailers()
//        parseTrailers.parse(result)
//    }
//
//    override fun doInBackground(vararg url: String?): String? {
//        Log.d(TAG, "it start with ${url[0]}")
//        val rssFeed = downloadXMl(url[0])
//        if (rssFeed.isNotEmpty())
//            println(rssFeed)
//        else
//            Log.e(TAG, "error downlodinf")
//        return ""
//    }
//
//    private fun downloadXMl(urlPath: String?): String {
//        return URL(urlPath).readText()
//    }
//}