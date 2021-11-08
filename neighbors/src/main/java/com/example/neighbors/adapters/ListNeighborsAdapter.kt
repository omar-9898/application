package com.example.neighbors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborsAdapter.ViewHolder
import com.example.neighbors.models.Neighbor
import com.example.neighbors.ui.fragments.ListNeighborsFragment
class ListNeighborsAdapter(items: List<Neighbor>,val  handler: ListNeighborsFragment) : RecyclerView.Adapter<ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.neighbor_item, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        // Display Neighbour Name
        holder.mNeighbourName.text = neighbour.name
// Display Neighbour Avatar
        Glide.with(holder.mNeighbourAvatar)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(holder.mNeighbourAvatar)
            holder.mDeleteButton.setOnClickListener{
                handler.onDeleteNeibor(neighbour)
            }
        holder.mNeighbourAvatar.setOnClickListener {
            handler.detail(neighbour)
        }
    }
    override fun getItemCount(): Int {
        return mNeighbours.size
    }
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mNeighbourAvatar: ImageView
        val mNeighbourName: TextView
        val mDeleteButton: ImageButton

        init {
            // Enable click on item

            mNeighbourAvatar = view.findViewById(R.id.item_list_avatar)
            mNeighbourName = view.findViewById(R.id.item_list_name)
            mDeleteButton = view.findViewById(R.id.item_list_delete_button)
        }
    }

    interface ListNeighborHandler {
        fun onDeleteNeibor(neighbor: Neighbor)
        fun detail(neighbor: Neighbor)

    }

}
