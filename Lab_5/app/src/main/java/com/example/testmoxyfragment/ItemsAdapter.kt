package com.example.testmoxyfragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class ItemsAdapter(var items: ArrayList<Items>?) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    private var onItemClickListener: View.OnClickListener? = null

    fun setItemClickListener(clickListener: View.OnClickListener) {
        onItemClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) { // chto esli tut prosto ViewHolder
        holder.weatherText.text = items!!.get(position).date + "\n" + items!!.get(position).temp + "\n" + items!!.get(position).cloud
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherText = view.weather_item_text

        init {
            view.tag = this
            view.setOnClickListener(onItemClickListener);
        }
    }
}