package com.example.neighbors.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.models.Neighbor

class DetailNeighboursFragment(neighbor: Neighbor): Fragment() {
    private lateinit var avatar: ImageView
    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var web: TextView
    private lateinit var adress: TextView
    private lateinit var about: TextView
    private lateinit var btnback:Button
    private lateinit var favorit: SwitchCompat
    private var neighbor = neighbor
     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail, container, false)
         (activity as? NavigationListener)?.let {
             onViewCreated(view, Bundle())
             it.updateTitle(R.string.detail)
         }
         btnback=view.findViewById(R.id.btn_back)
        avatar = view.findViewById(R.id.avatar_detail)
        name = view.findViewById(R.id.Name_detail)
        phone = view.findViewById(R.id.phone_detail)
        web = view.findViewById(R.id.web_detail)
        adress = view.findViewById(R.id.adress_detail)
        about = view.findViewById(R.id.about_detail)

         name.text=neighbor.name
         phone.text=neighbor.phoneNumber
         web.text=neighbor.webSite
         adress.text=neighbor.address
         about.text=neighbor.aboutMe
         Glide.with(this)
             .load(neighbor.avatarUrl)
             .apply(RequestOptions.circleCropTransform())
             .placeholder(R.drawable.ic_baseline_person_outline_24)
             .error(R.drawable.ic_baseline_person_outline_24)
             .skipMemoryCache(false)
             .into(avatar)

         btnback.setOnClickListener{
             (activity as? NavigationListener)?.let {
                 it.showFragment(ListNeighborsFragment())
             }
         }

         favorit=view.findViewById(R.id.favorit)
             favorit.setOnCheckedChangeListener { _, b ->
                 print(b)
             }


        return view
    }
    }