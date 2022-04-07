package com.revature.project2.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.revature.project2.model.api.alltoys.ToyItem

@Dao
interface UserToyDAO {

    @Query("SELECT * FROM usertoys")
    fun fetchAllUserToys(): LiveData<List<ToyItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToy(toy: ToyItem)

    @Query("DELETE FROM usertoys WHERE id=:id")
    suspend fun deleteUserToyById(id: Int)

    @Query("DELETE FROM usertoys")
    suspend fun deleteUserToys()
}