package com.example.neighbors.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborsAdapter
import com.example.neighbors.models.Neighbor
import com.example.neighbors.repositories.NeighborRepository
import com.example.neighbors.viewmodels.NeighborViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListNeighborsFragment:Fragment() {
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    private lateinit var viewModel: NeighborViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.liste)
        }
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        var addNeighbor=view.findViewById<FloatingActionButton>(R.id.add_neighbor)
        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setData()

    }
    private lateinit var recyclerView: RecyclerView
    fun onDeleteNeibor(neighbor: Neighbor) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Neighbor Alert")
        alertDialogBuilder.setMessage("Voulez-vous supprimer ${neighbor.name} ?")
        alertDialogBuilder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(context,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
            NeighborRepository.getInstance().deleteNeighbour(neighbor)
            //on refresh
            this.setData()
        }

        alertDialogBuilder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(context,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.show()
    }
    fun detail(neighbor: Neighbor) {
        (activity as? NavigationListener)?.let {
            it.showFragment(DetailNeighboursFragment(neighbor))
        }
            this.setData()


    }
    fun setData() {
        viewModel.neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            recyclerView.adapter = adapter
        }
    }

}
