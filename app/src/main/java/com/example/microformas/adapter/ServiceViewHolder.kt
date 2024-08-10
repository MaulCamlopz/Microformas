package com.example.microformas.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.microformas.R
import com.example.microformas.model.ServiceModel

class ServiceViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    val serviceTitle = view.findViewById<TextView>(R.id.item_service_title)
    val serviceSubTitle = view.findViewById<TextView>(R.id.item_service_subtitle)

    fun render(service: ServiceModel) {
        serviceTitle.text = service.description
        serviceSubTitle.text = service.id.toString()
    }

}