package com.geartocare.geartocare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheckListAdapter(private val dataset: ArrayList<CheckList>,private val listener: ItemClicked) :
    RecyclerView.Adapter<CheckListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val vh = ViewHolder(view)
        view.setOnLongClickListener {
            listener.onItemClicked(dataset[vh.adapterPosition].itemText.toString(),dataset[vh.adapterPosition].id.toString())

            true
        }
        return vh
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]
        holder.itemText.text = item.itemText
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.tv_item)
    }

}

interface ItemClicked {
    fun onItemClicked(item:String,id:String)
}

