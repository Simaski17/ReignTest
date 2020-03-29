package com.jimmyhernandez.reigntest.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jimmyhernandez.domain.HighlightResult

@Entity
data class Hit(
    @SerializedName("created_at")
    @Expose
    @ColumnInfo(name = "created_at") var createdAt: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("author")
    @Expose
    var author: String? = null,
    @SerializedName("points")
    @Expose
    var points: Int? = null,
    @SerializedName("story_text")
    @Expose
    @ColumnInfo(name = "story_text") var storyText: String? = null,
    @SerializedName("comment_text")
    @Expose
    @ColumnInfo(name = "comment_text") var commentText: String? = null,
    @SerializedName("num_comments")
    @Expose
    @ColumnInfo(name = "num_comments") var numComments: Int? = null,
    @SerializedName("story_id")
    @Expose
    @ColumnInfo(name = "story_id") var storyId: Int? = null,
    @SerializedName("story_title")
    @Expose
    @ColumnInfo(name = "story_title") var storyTitle: String? = null,
    @SerializedName("story_url")
    @Expose
    @ColumnInfo(name = "story_url") var storyUrl: String? = null,
    @SerializedName("parent_id")
    @Expose
    @ColumnInfo(name = "parent_id") var parentId: Int? = null,
    @SerializedName("created_at_i")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "created_at_i") var createdAtI: Int? = null,
    @SerializedName("_tags")
    @Expose
    @ColumnInfo(name = "created_at_i")
    @Ignore var tags: List<String>? = null,
    @SerializedName("objectID")
    @Expose
    var objectID: String? = null,
    @SerializedName("_highlightResult")
    @Expose
    @Ignore var highlightResult: HighlightResult? = null,
    var delete: Boolean = false
)