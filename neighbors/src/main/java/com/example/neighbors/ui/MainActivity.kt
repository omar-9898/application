package com.example.neighbors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.dal.memory.InMemoryNeighborDataSource
import com.example.neighbors.dal.room.RoomNeighborDataSource
import com.example.neighbors.databinding.ActivityMainBinding
import com.example.neighbors.databinding.ActivityMainBinding.inflate
import com.example.neighbors.di.DI
import com.example.neighbors.repositories.NeighborRepository
import com.example.neighbors.ui.fragments.ListNeighborsFragment


class MainActivity : AppCompatActivity(), NavigationListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        fragment = ListNeighborsFragment()
        binding.switchPersist.setOnCheckedChangeListener { _, b ->
            if(b){NeighborRepository.getInstance().switchDataSource(RoomNeighborDataSource(application))}
            else{NeighborRepository.getInstance().switchDataSource(InMemoryNeighborDataSource())}

            (fragment as? ListNeighborsFragment)?.setData()
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showFragment(ListNeighborsFragment())
    }

    override fun showFragment(fragment: Fragment) {
        this.fragment = fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()

    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }


}