package com.jimmyhernandez.domain

data class Hit(
    val createdAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val author: String? = null,
    val points: Int? = null,
    val storyText: Any? = null,
    val commentText: Any? = null,
    val numComments: Int? = null,
    val storyId: Any? = null,
    val storyTitle: Any? = null,
    val storyUrl: Any? = null,
    val parentId: Any? = null,
    val createdAtI: Int? = null,
    val tags: List<String>? = null,
    val objectID: String? = null,
    val highlightResult: HighlightResult? = null
)