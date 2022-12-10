package com.djr.tabnews.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Transaction
import com.djr.tabnews.core.database.entities.OfflinePostEntity
import com.djr.tabnews.core.database.entities.PostTagCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {

    @Query("SELECT * FROM saved_posts ORDER BY created_at")
    fun getSavedPosts(): Flow<List<OfflinePostEntity>>

    @Transaction
    @Query(
        """
            SELECT * FROM saved_posts WHERE id IN (
                SELECT post_id FROM posts_cross_tags WHERE tag_name IN (:filterTags) 
            ) ORDER BY created_at
        """
    )
    fun getSavedPosts(
        filterTags: Set<String> = emptySet()
    ): Flow<List<OfflinePostEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM saved_posts WHERE id = :postId)")
    suspend fun isPostSaved(postId: String): Boolean

    @Insert(onConflict = ABORT)
    suspend fun savePost(post: OfflinePostEntity)

    @Insert(onConflict = IGNORE)
    suspend fun saveOrIgnoreCrossRef(post: PostTagCrossRef)

    @Query("DELETE FROM saved_posts WHERE id = :postId")
    suspend fun deletePost(postId: String)

}
