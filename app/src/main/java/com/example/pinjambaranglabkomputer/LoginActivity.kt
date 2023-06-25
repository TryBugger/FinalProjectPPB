package com.example.pinjambaranglabkomputer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class LoginActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference

    private lateinit var textId: EditText
    private lateinit var textPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        textId = findViewById(R.id.userId)
        textPassword = findViewById(R.id.userPassword)
    }

    fun buttonLoginClick(view: View?){
        if(textId.text.toString().isEmpty()) {
            textId.error = "Nama Tidak Boleh Kosong"
            textId.requestFocus()
        } else if(textPassword.text.toString().isEmpty()) {
            textPassword.error = "Password Tidak Boleh Kosong"
            textPassword.requestFocus()
        } else {
            databaseReference = FirebaseDatabase.getInstance("https://pinjam-barang-lab-komputer-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("ListUser")
            val query: Query = databaseReference.orderByChild("username").equalTo(textId.text.toString())
            query.addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(item in snapshot.children){
                            val user = item.getValue<Users>()
                            if(user != null){
                                if(user.password.equals((textPassword.text.toString()))){
                                    if(user.userAccesslevel.equals("1")){
                                        val intent = Intent(this@LoginActivity, AdminActivity::class.java).apply {
                                            putExtra("adminName", textId.text.toString())
                                        }
                                        if (intent.resolveActivity(packageManager) != null) {
                                            startActivity(intent)
                                        }
                                    } else if(user.userAccesslevel.equals("2")){
                                        val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                                            putExtra("adminName", textId.text.toString())
                                        }
                                        if (intent.resolveActivity(packageManager) != null) {
                                            startActivity(intent)
                                        }
                                    }
                                }
                            } else {
                                TODO("Buat Kondisi Jikia Password Salah")
                            }
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Username Belum Terdaftar", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_LONG).show()
                }
            })
        }

//        iudin
    }
}