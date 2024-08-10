package com.example.microformas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.microformas.model.ServiceAPIFactory
import com.example.microformas.model.UserModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.input_text_username)
        val passEditText = findViewById<EditText>(R.id.input_text_pass)

        val progressBar = findViewById<ProgressBar>(R.id.progress_login)

        findViewById<Button>(R.id.btn_login)
            .setOnClickListener {

                progressBar.visibility = View.VISIBLE

                val username = usernameEditText.text.toString()
                val pass = passEditText.text.toString()

                Log.d("LOGIN VALUES","username = ${username} - password = ${pass}")

                val service = ServiceAPIFactory.makeLoginAPI()

                lifecycleScope.launch {
                    try {
                        val user = UserModel(
                            username = "android",
                            password = "LuneS2024*",
                            version = "1.0.0",
                            origin = "mobile"
                        )
                        val response = service.postDataLogin(user)

                        progressBar.visibility = View.GONE

                        val token = response.token

                        if (token.isNotEmpty()) {
                            val intent = Intent(baseContext, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            runOnUiThread {
                                val text = "INVALID USER"
                                val duration = Toast.LENGTH_SHORT
                                val toast = Toast.makeText(baseContext, text, duration)
                                toast.show()
                            }
                        }

                    } catch (e: Exception) {
                        Log.e("Error", "Exception", e)
                        runOnUiThread {
                            val text = "Error"
                            val duration = Toast.LENGTH_SHORT
                            val toast = Toast.makeText(baseContext, text, duration)
                            toast.show()
                        }
                    }
                }
            }
    }
}