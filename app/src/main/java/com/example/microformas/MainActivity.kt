package com.example.microformas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.microformas.adapter.ServiceAdapter
import com.example.microformas.model.ServiceAPIFactory
import com.example.microformas.model.ServiceModel
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    var serviceList: List<ServiceModel> = listOf()

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
                serviceList = response.data
                println("SERVICE_LIST ON_RESPONSE - SIZE = ${serviceList.size}")
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    initRecyclerView()
                }
            } catch (e: SocketTimeoutException) {
                Log.e("Error", "Timeout exception")
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    val text = "Tiemout"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(baseContext, text, duration)
                    toast.show()
                }
            } catch (e: Exception) {
                Log.e("Error", "${e.message}")
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    val text = "ERROR"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(baseContext, text, duration)
                    toast.show()
                }
            }

        }

    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_serviceList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ServiceAdapter(serviceList)
        recyclerView.visibility = View.VISIBLE
    }

}
