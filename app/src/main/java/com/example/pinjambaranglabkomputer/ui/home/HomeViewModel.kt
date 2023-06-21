package com.example.nav_drawer.ui.home

import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "List Alat Laboratorium"
    }
    val text: LiveData<String> = _text
/*
    val listAlat = arrayOf("Arduino","Laptop","Kabel Lan", "Charger Laptop", "VR")

    val listAlatAdapter : ArrayAdapter<String> = ArrayAdapter(
        this,android.R.layout.simple_list_item_1,
        listAlat
    )
*/


}