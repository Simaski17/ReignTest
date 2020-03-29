package com.jimmyhernandez.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("author")
    @Expose
    val author: String? = null,
    @SerializedName("points")
    @Expose
    val points: Int? = null,
    @SerializedName("story_text")
    @Expose
    val storyText: String? = null,
    @SerializedName("comment_text")
    @Expose
    val commentText: String? = null,
    @SerializedName("num_comments")
    @Expose
    val numComments: Int? = null,
    @SerializedName("story_id")
    @Expose
    val storyId: Int? = null,
    @SerializedName("story_title")
    @Expose
    val storyTitle: String? = null,
    @SerializedName("story_url")
    @Expose
    val storyUrl: String? = null,
    @SerializedName("parent_id")
    @Expose
    val parentId: Int? = null,
    @SerializedName("created_at_i")
    @Expose
    val createdAtI: Int? = null,
    @SerializedName("_tags")
    @Expose
    val tags: List<String>? = null,
    @SerializedName("objectID")
    @Expose
    val objectID: String? = null,
    @SerializedName("_highlightResult")
    @Expose
    val highlightResult: HighlightResult? = null
)