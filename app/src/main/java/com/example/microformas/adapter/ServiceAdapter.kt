package com.example.microformas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.microformas.R
import com.example.microformas.model.ServiceModel

class ServiceAdapter(private val serviceList: List<ServiceModel>): RecyclerView.Adapter<ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServiceViewHolder(layoutInflater.inflate(R.layout.item_service, parent, false))
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = serviceList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = serviceList.size

}