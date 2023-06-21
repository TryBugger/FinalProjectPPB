package com.example.nav_drawer.ui.form

import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Form Peminjaman Alat"
    }
    val text: LiveData<String> = _text
}