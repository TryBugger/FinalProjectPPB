package com.example.pinjambaranglabkomputer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var textId: EditText
    lateinit var textNIM: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textId = findViewById(R.id.userId)
        textNIM = findViewById(R.id.userNIM)
    }

    fun buttonLoginClick(view: View?){
        val intent = Intent(this, AdminActivity::class.java).apply {
            putExtra("adminName", textId.text.toString())
        }

        println(textId.text)
        println(textNIM.text)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}