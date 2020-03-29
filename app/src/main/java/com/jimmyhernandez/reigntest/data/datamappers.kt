package com.jimmyhernandez.reigntest.data

import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.reigntest.data.database.Hit as DomainNews

fun Hit.toRoomNews(): DomainNews =
    DomainNews(
        createdAt,
        title,
        url,
        author,
        points,
        storyText,
        commentText,
        numComments,
        storyId,
        storyTitle,
        storyUrl,
        parentId,
        createdAtI,
        tags,
        objectID,
        highlightResult
    )

fun DomainNews.toDamainNews(): Hit =
    Hit(
        createdAt,
        title,
        url,
        author,
        points,
        storyText,
        commentText,
        numComments,
        storyId,
        storyTitle,
        storyUrl,
        parentId,
        createdAtI,
        tags,
        objectID,
        highlightResult
    )