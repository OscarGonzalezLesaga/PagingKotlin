package com.oscar.gonzalez.lesaga.paging.presentation

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.oscar.gonzalez.lesaga.paging.R.id
import com.oscar.gonzalez.lesaga.paging.R.layout
import com.oscar.gonzalez.lesaga.paging.data.Item
import com.oscar.gonzalez.lesaga.paging.data.Owner
import com.oscar.gonzalez.lesaga.paging.presentation.ItemAdapter.ItemViewHolder
import com.squareup.picasso.Picasso

class ItemAdapter(val context: Context) : PagedListAdapter<Item, ItemViewHolder>(
    diff_callback
) {

    var itemListener: ((userName: Owner) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ItemViewHolder(
            LayoutInflater.from(context).inflate(
                layout.recyclerview_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)


        item?.let {
            Picasso.get().load(it.owner.profile_image).into(holder.imageView)
            holder.textView?.text = item.owner.display_name

            holder.itemView.setOnClickListener {
                itemListener?.invoke(item.owner)
            }
        }
    }

    companion object {
        val diff_callback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.answer_id == newItem.answer_id

            override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView? = null
        var textView: TextView? = null

        init {
            imageView = itemView.findViewById(id.imageView)
            textView = itemView.findViewById(id.textViewName)
        }
    }
}