package com.example.pinjambaranglabkomputer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nav_drawer.ui.home.HomeViewModel
import com.example.pinjambaranglabkomputer.BarangAdapter
import com.example.pinjambaranglabkomputer.ListBarang
import com.example.pinjambaranglabkomputer.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var barangListRecyclerView: RecyclerView
    private lateinit var barangListArrayList: ArrayList<ListBarang>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        barangListRecyclerView = binding.barangRecycleView
        barangListRecyclerView.layoutManager = LinearLayoutManager(this)
        barangListRecyclerView.setHasFixedSize(true)

        barangListArrayList = arrayListOf<ListBarang>()
        barangListRecyclerView.adapter = BarangAdapter(barangListArrayList)

        listBarang()
/*        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    private fun listBarang(){
        databaseReference = FirebaseDatabase.getInstance("https://pinjam-barang-lab-komputer-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("ListBarang")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                barangListArrayList.clear()
                if(snapshot.exists()){
                    for(baranglistSnapshot in snapshot.children){
                        val barang = baranglistSnapshot.getValue(ListBarang::class.java)
                        barang?.let {
                            barangListArrayList.add(it)
                        }
                    }
                    barangListRecyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error, if necessary
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}