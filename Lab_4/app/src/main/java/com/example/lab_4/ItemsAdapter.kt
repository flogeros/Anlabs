package com.example.lab_4

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class ItemsAdapter(private var items: ArrayList<Items>?) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.weatherText.text = items!![position].date + "\n" + items!![position].temp + "\n" + items!![position].cloud
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherText = view.weather_item_text!!

        init {
            view.tag = this
            view.setOnClickListener(onItemClickListener)
        }
    }
}