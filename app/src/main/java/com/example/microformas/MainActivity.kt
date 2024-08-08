package com.example.microformas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.microformas.model.ServiceAPIFactory
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progress_circular_main)


        val button = findViewById<Button>(R.id.btn_location)
        button.setOnClickListener { v ->
            startActivity(
                Intent(
                    v.context,
                    MapsActivity::class.java
                )
            )
        }

        val service = ServiceAPIFactory.makeServiceAPI()

        lifecycleScope.launch {

            try {
                val response = service.getServiceData()

                val descriptionList: MutableList<String> = mutableListOf()
                val idList: MutableList<Long> = mutableListOf()

                response.data.forEach {
                    descriptionList.add(it.description)
                    idList.add(it.id)
                }

                println("LIST RESPONSE - SIZE = ${idList.count()}")

                runOnUiThread {
                    val serviceListView = findViewById<ListView>(R.id.serviceList)
                    val arrayAdapter: ArrayAdapter<*> =
                        ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, descriptionList)
                    serviceListView.adapter = arrayAdapter
                    progressBar.visibility = View.GONE
                    serviceListView.visibility = View.VISIBLE
                }

            } catch (e: SocketTimeoutException) {
                Log.e("Error", "Timeout exception", e)
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    val text = "Tiemout"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(baseContext, text, duration)
                    toast.show()
                }
            }

        }

    }

}
