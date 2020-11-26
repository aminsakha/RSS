package com.rss


import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.FileDescriptor.err
import java.lang.System.*
import java.util.*

class ParseTrailers {
    val TAG = "test"
   val trailers = ArrayList<FeedEntry>()
    fun parse(xmlData: String): Boolean {
        Log.d(TAG, "parse called with $xmlData")
        var status = true
        var inItem = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xpp.name?.toLowerCase(Locale.ROOT)
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        Log.d(TAG, "starting tag for $tagName")
                        if (tagName == "item") {
                            inItem = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
                        Log.d(TAG, "ending tag for $tagName")
                        if (inItem) {
                            when (tagName) {
                                "item" -> {
                                    trailers.add(currentRecord)
                                    inItem = false
                                    currentRecord = FeedEntry()
                                }
                                "description" -> currentRecord.summary = textValue
                                "pubdate" -> currentRecord.releaseDate = textValue
                                "title" -> currentRecord.name = textValue
                            }
                        }

                    }
                }
                eventType = xpp.next()
            }
            for (trailer in trailers) {
                System.err.println("*****************")
                println(trailer.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            status = false
        }
        return status
    }

}