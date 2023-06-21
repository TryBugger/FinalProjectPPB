package com.example.pinjambaranglabkomputer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pinjambaranglabkomputer.databinding.ActivityLaporanBinding
import com.google.firebase.database.*

class LaporanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaporanBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var peminjamanRecycleView: RecyclerView
    private lateinit var peminjamanArrayList: ArrayList<ListPeminjaman>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        peminjamanRecycleView = findViewById(R.id.laporanRecycle)
        peminjamanRecycleView.layoutManager = LinearLayoutManager(this)
        peminjamanRecycleView.setHasFixedSize(true)

        peminjamanArrayList = arrayListOf<ListPeminjaman>()
        readLaporan()
    }

    private fun readLaporan(){
        databaseReference = FirebaseDatabase.getInstance().getReference("ListPeminjaman")
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(peminjamanSnapshot in snapshot.children){
                        val peminjaman = peminjamanSnapshot.getValue(ListPeminjaman::class.java)
                        peminjamanArrayList.add(peminjaman!!)
                    }
                    println(peminjamanArrayList)
                    peminjamanRecycleView.adapter = PeminjamanAdapter(peminjamanArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}