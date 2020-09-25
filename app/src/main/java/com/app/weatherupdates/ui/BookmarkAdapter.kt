package com.app.weatherupdates.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.weatherupdates.R
import com.app.weatherupdates.databinding.ItemBookmarkedLocationBinding
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation

class BookmarkAdapter(var items: MutableList<BookMarkedLocation>,
                      var itemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setData(items: MutableList<BookMarkedLocation>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookMarkViewHolder(
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_bookmarked_location,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookMarkViewHolder) {
            holder.bind(position)
            holder.itemView.setOnClickListener(holder)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class BookMarkViewHolder(private val itemBookmarkedLocationBinding: ItemBookmarkedLocationBinding) :
            RecyclerView.ViewHolder(itemBookmarkedLocationBinding.root), View.OnClickListener {

        override fun onClick(v: View?) {
            itemClick.invoke(adapterPosition)
        }

        fun bind(position: Int) {
            itemBookmarkedLocationBinding.locationModel = items[position]
            itemBookmarkedLocationBinding.executePendingBindings()
        }

    }
}