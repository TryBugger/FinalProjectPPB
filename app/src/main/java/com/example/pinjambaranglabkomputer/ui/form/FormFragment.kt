package com.example.pinjambaranglabkomputer.ui.form

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav_drawer.ui.form.FormViewModel
import com.example.pinjambaranglabkomputer.databinding.FragmentFormBinding

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

        val nextBtn : Button = binding.kirim
        nextBtn.setOnClickListener{
            TODO("Persiapan Inject Data ke Firebase")
/*            val fragment = StatusFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()*/
        }
/*        val textView: TextView = binding.toolbar
        formViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

/*        // Spinner
        val spinner = binding.spinner
        val spinnerValues = arrayOf("Arduino", "Kabel LAN", "Laptop", "Charger Laptop")
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, spinnerValues)
        spinner.adapter = spinnerAdapter*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}