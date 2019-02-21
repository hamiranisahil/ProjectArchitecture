package com.example.library.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MultipleRecyclerViewAdapter(
    val context: Context,
    val recyclerView: RecyclerView,
    val layout: Int,
    val list: List<Any>,
    val onBindViewHolderListener: OnBindViewHolderListener
) : RecyclerView.Adapter<MultipleRecyclerViewAdapter.CommonViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: CommonViewHolder, position: Int) {
        onBindViewHolderListener.onViewBind(recyclerView, viewHolder, position)
    }

    class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    interface OnBindViewHolderListener {
        fun onViewBind(recyclerView: RecyclerView, viewHolder: CommonViewHolder, position: Int)
    }

}