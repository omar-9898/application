package com.example.neighbors.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neighbors.dal.NeighborDatasource

import com.example.neighbors.models.Neighbor

class InMemoryNeighborDataSource : NeighborDatasource {
    private val observable: LiveData<List<Neighbor>> by lazy { MutableLiveData(InMemory_NeighborS) }
    override val neighbours: LiveData<List<Neighbor>>
        get() = observable

    override fun deleteNeighbour(neighbor: Neighbor) {
        InMemory_NeighborS.remove(neighbor)
    }

    override fun createNeighbour(neighbor: Neighbor) {
        InMemory_NeighborS.add(neighbor)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
    }

    override fun updateDataNeighbour(neighbor: Neighbor) {
        InMemory_NeighborS[InMemory_NeighborS.indexOf(neighbor)]=neighbor
    }
}



