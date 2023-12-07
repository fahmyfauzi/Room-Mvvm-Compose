package com.example.roommvvmcompose.di

import android.content.Context
import androidx.room.Room
import com.example.roommvvmcompose.data.UserDatabase
import com.example.roommvvmcompose.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) : UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        "user_table"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(userDatabase : UserDatabase) : UserDao = userDatabase.userDao()
}