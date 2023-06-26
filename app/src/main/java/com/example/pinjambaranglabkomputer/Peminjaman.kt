package com.example.pinjambaranglabkomputer

data class Peminjaman(
    var nama: String? = null,
    var nim: String? = null,
    var alat: String? = null,
    var jumlah: Long? = null,
    var tanggal_pinjam: String? = null,
    var tanggal_kembali: String? = null,
    var alasan: String? = null,
    var status_peminjaman: String? = null,
)

