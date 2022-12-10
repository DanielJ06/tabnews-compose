package com.djr.tabnews.core.database.di

import android.content.Context
import androidx.room.Room
import com.djr.tabnews.core.database.TabnewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): TabnewsDatabase {
        return Room.databaseBuilder(
            context,
            TabnewsDatabase::class.java,
            "tabnews-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}
