package com.cclean.mover.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cclean.mover.PreceptItem

@Database(entities = [PreceptItem::class], version = 1)
abstract class PreceptDB: RoomDatabase() {
    abstract fun PreceptDao(): preceptDAO

    companion object {
        private var INSTANCE: PreceptDB? = null

        fun getInstance(context: Context): PreceptDB? {
            if (INSTANCE == null) {
                synchronized(PreceptDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            PreceptDB::class.java, "precept.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}