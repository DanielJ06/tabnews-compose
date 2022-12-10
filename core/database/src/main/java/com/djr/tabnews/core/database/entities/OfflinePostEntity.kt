package com.djr.tabnews.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_posts")
data class OfflinePostEntity(
    @PrimaryKey
    val id: String,
    val ownerId: String,
    val slug: String,
    val title: String,
    val ownerUsername: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
