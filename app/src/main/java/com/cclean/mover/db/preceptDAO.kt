package com.cclean.mover.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.cclean.mover.PreceptItem

@Dao
interface preceptDAO {
    @Query("SELECT * FROM precept")
    fun getAll(): List<PreceptItem>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = REPLACE)
    fun insert(cat: PreceptItem)

    @Query("DELETE from precept")
    fun deleteAll()
}