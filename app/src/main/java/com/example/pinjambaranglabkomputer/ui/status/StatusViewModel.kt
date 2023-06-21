package com.example.nav_drawer.ui.status

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Peminjaman Alat Kamu Sedang Di Proses"
    }
    val text: LiveData<String> = _text
}