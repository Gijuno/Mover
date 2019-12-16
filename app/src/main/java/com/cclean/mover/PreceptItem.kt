package com.cclean.mover

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "precept")
public class PreceptItem(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "content") var content: String,
        @ColumnInfo(name = "author") var author: String,
        @ColumnInfo(name = "answer") var answer: String) : Serializable