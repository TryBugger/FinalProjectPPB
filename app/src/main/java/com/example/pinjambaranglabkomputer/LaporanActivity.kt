package com.example.pinjambaranglabkomputer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LaporanActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var peminjamanRecycleView: RecyclerView
    private lateinit var peminjamanArrayList: ArrayList<ListPeminjaman>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        peminjamanRecycleView = findViewById(R.id.laporanRecycle)
        peminjamanRecycleView.layoutManager = LinearLayoutManager(this)
        peminjamanRecycleView.setHasFixedSize(true)

        peminjamanArrayList = arrayListOf<ListPeminjaman>()
        peminjamanRecycleView.adapter = PeminjamanAdapter(peminjamanArrayList)
        readLaporan()
    }

    private fun readLaporan(){
        databaseReference = FirebaseDatabase.getInstance().getReference("ListPeminjaman")
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(peminjamanSnapshot in snapshot.children){
                        val peminjaman = peminjamanSnapshot.getValue(ListPeminjaman::class.java)
                        peminjaman?.let {
                            peminjamanArrayList.add(it)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("LaporanActivity", "Error: ${error.message}")
            }

        })
    }
}