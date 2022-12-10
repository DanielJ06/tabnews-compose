package com.djr.tabnews.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "posts_cross_tags",
    primaryKeys = ["post_id", "tag_name"],
    foreignKeys = [
        ForeignKey(
            entity = OfflinePostEntity::class,
            parentColumns = ["id"],
            childColumns = ["post_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["tag_name"],
            childColumns = ["tag_name"],
            onDelete = CASCADE
        )
    ]
)
data class PostTagCrossRef(
    @ColumnInfo(name = "post_id")
    val postId: String,
    @ColumnInfo(name = "tag_name")
    val tagName: String
)