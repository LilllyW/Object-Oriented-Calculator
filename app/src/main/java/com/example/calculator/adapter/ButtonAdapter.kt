package com.example.calculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R

class ButtonAdapter(private val list: List<String>) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.onBind(data)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val button : Button = view.findViewById(R.id.result_button)

        fun onBind(data:String){
            button.text = data
            button.tag = data
        }
    }
}