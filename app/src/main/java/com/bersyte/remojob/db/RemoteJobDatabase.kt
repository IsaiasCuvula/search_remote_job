package com.bersyte.remojob.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bersyte.remojob.models.Job
import com.bersyte.remojob.models.JobToSave

@Database(entities = [JobToSave::class], version = 1)
abstract class RemoteJobDatabase : RoomDatabase() {

    abstract fun getRemoteJobDao(): RemoteJobDao

    companion object {
        @Volatile
        private var instance: RemoteJobDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RemoteJobDatabase::class.java,
                "remoteJob_db2"
            ).build()
    }
}