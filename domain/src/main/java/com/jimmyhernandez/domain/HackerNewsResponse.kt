package com.jimmyhernandez.domain

data class HackerNewsResponse(
    val hits: List<Hit>? = null,
    val nbHits: Int? = null,
    val page: Int? = null,
    val nbPages: Int? = null,
    val exhaustiveNbHits: Boolean? = null,
    val query: String? = null,
    val params: String? = null,
    val processingTimeMS: Int? = null
)