package com.example.pinjambaranglabkomputer.ui.form

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav_drawer.ui.form.FormViewModel
import com.example.pinjambaranglabkomputer.Peminjaman
import com.example.pinjambaranglabkomputer.databinding.FragmentFormBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val formViewModel =
            ViewModelProvider(this).get(FormViewModel::class.java)

        _binding = FragmentFormBinding.inflate(inflater, container, false)
        val root: View = binding.root

/*        val textView: TextView = binding.toolbar
        formViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        // Spinner
//        val spinnerValues = arrayOf("Arduino", "Kabel LAN", "Laptop")
//        val spinnerAdapter = ArrayAdapter(this@FormFragment, R.layout.simple_spinner_dropdown_item, spinnerValues)
//        binding.spinnerAlat.adapter = spinnerAdapter

        val nextBtn : Button = binding.kirim
        nextBtn.setOnClickListener{
            uploadData()
        }

        return root
    }

    fun uploadData() {
        val database = FirebaseDatabase.getInstance("https://pinjam-barang-lab-komputer-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("ListPeminjaman")
        val dataUpload = Peminjaman(
            binding.textNameField.text.toString(),
            binding.textNIMField.text.toString(),
            "Arduino",//binding.spinnerAlat.selectedItem.toString(),
            1,
            binding.textTglPeminjamanField.text.toString(),
            binding.textTglPengembalianField.text.toString(),
            binding.textAlasanField.text.toString(),
            "process"
        )
        var totalPinjaman: Long = 0
//        database.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                totalPinjaman = snapshot.childrenCount + 1
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                totalPinjaman = snapshot.childrenCount
                // Use the updated value here
                println("Didalam Fungsi" + totalPinjaman.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error, if necessary
            }
        }
        database.addValueEventListener(valueEventListener)
        println("Diluar Fungsi" + totalPinjaman.toString())

            database.child(totalPinjaman.toString()).setValue(dataUpload).addOnSuccessListener {
                binding.textNameField.text!!.clear()
                binding.textNIMField.text!!.clear()
    //            binding.spinnerAlat.adapter(null)
                binding.textTglPeminjamanField.text!!.clear()
                binding.textTglPengembalianField.text!!.clear()
                binding.textAlasanField.text!!.clear()

    //            Toast.makeText(this@FormFragment, "Upload Data Peminjaman Sukses", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
//            Toast.makeText(this@FormFragment, "Gagal Upload Data Peminjaman", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}