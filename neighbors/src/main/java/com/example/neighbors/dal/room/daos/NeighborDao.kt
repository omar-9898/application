package com.example.neighbors.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.neighbors.dal.room.entities.NeighborEntity
@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Query("SELECT * from neighbors where id = :id")
    fun getNeighbourById(id: Long): LiveData<NeighborEntity>

    @Insert()
    fun add(neighbor: NeighborEntity)

    @Delete
    fun delete(neighbor: NeighborEntity)

    @Update
    fun update(neighbor: NeighborEntity)

}
