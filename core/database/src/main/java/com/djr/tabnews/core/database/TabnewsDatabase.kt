package com.djr.tabnews.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.djr.tabnews.core.database.daos.PostsDao
import com.djr.tabnews.core.database.daos.TagsDao
import com.djr.tabnews.core.database.entities.OfflinePostEntity
import com.djr.tabnews.core.database.entities.PostTagCrossRef
import com.djr.tabnews.core.database.entities.TagEntity

@Database(
    version = 3,
    entities = [
        OfflinePostEntity::class,
        TagEntity::class,
        PostTagCrossRef::class
    ]
)
abstract class TabnewsDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun tagsDao(): TagsDao
}