package com.example.neighbors.repositories
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.neighbors.dal.NeighborDatasource
import com.example.neighbors.dal.memory.InMemoryNeighborDataSource
import com.example.neighbors.dal.room.RoomNeighborDataSource
import com.example.neighbors.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private var apiService: NeighborDatasource
    private val memoryDataSource: InMemoryNeighborDataSource = InMemoryNeighborDataSource()
    private val roomDataSource: RoomNeighborDataSource = RoomNeighborDataSource(application)
    init {
        apiService = InMemoryNeighborDataSource()
    }
    fun createNeighbour(Neighbor:Neighbor) =apiService.createNeighbour(Neighbor)
    fun getNeighbours(): LiveData<List<Neighbor>> = apiService.neighbours
    fun deleteNeighbour(Neighbor:Neighbor) = apiService.deleteNeighbour(Neighbor)
    fun updatefav(neighbor: Neighbor,favorit:Boolean)=apiService.updateFavoriteStatus(Neighbor(
        neighbor.id,neighbor.name  , neighbor.avatarUrl ,neighbor.address,neighbor.phoneNumber,neighbor.aboutMe,favorit,neighbor.webSite))
    fun switchDataSource(switch: NeighborDatasource) {
        apiService = switch
    }
    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                throw Error()
            }
            return instance!!
        }
    }
}


