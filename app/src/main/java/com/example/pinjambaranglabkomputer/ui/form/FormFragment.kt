package com.example.pinjambaranglabkomputer.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    private fun uploadData() {
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
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val totalPinjaman = snapshot.childrenCount + 1
                database.child(totalPinjaman.toString()).setValue(dataUpload).addOnSuccessListener {
                    // Clear input fields
                    binding.textNameField.text!!.clear()
                    binding.textNIMField.text!!.clear()
                    //binding.spinnerAlat.adapter(null)
                    binding.textTglPeminjamanField.text!!.clear()
                    binding.textTglPengembalianField.text!!.clear()
                    binding.textAlasanField.text!!.clear()
                }.addOnFailureListener {
                    // Handle the failure, if necessary
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