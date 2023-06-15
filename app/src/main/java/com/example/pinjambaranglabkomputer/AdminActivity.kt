package com.example.pinjambaranglabkomputer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class AdminActivity : AppCompatActivity() {
    lateinit var userText: EditText

//    val name = intent.getStringExtra("Name")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val name = intent.getStringExtra("Name").toString()

        userText = findViewById(R.id.adminId)
        userText.setText(name)
    }
}