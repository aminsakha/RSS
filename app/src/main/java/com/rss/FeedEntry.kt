package com.rss

import java.util.*

class FeedEntry {
    lateinit var name: String
    lateinit var summary: String
    lateinit var releaseDate: String
    override fun toString(): String {
        return """
            title = $name
            description = $summary
            publishDate=$releaseDate
            """.trimIndent()
    }
}