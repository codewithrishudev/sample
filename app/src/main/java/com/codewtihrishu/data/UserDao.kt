package com.codewtihrishu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewtihrishu.model.ResultsItem

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addvideo(resultsItem: ResultsItem)

    @Query("SELECT * FROM video_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<ResultsItem>>

}