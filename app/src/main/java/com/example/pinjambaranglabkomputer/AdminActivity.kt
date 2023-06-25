package com.example.pinjambaranglabkomputer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AdminActivity : AppCompatActivity() {
    lateinit var userText: EditText

//    val name = intent.getStringExtra("Name")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        supportActionBar?.hide()

        val name = intent.getStringExtra("adminName").toString()

        userText = findViewById(R.id.adminId)
        userText.setText(name)
    }

    fun buttonLaporanClick(view: View?){
        val intent = Intent(this, LaporanActivity::class.java)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}