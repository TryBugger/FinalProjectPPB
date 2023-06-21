package com.example.pinjambaranglabkomputer

data class ListPeminjaman(
    var nama: String? = null,
    var alat: String? = null,
    var jumlah: String? = null,
    var tanggal_pinjam: String? = null,
    var tanggal_kembali: String? = null,
    var status_peminjaman: String? = null,
)
