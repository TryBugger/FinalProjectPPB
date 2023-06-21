package com.example.pinjambaranglabkomputer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeminjamanAdapter(private val peminjamanList: ArrayList<ListPeminjaman>): RecyclerView.Adapter<PeminjamanAdapter.PeminjamanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeminjamanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listpeminjaman_layout, parent, false)
        return PeminjamanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PeminjamanViewHolder, position: Int) {
        val currentitem = peminjamanList[position]

        holder.nama.text = currentitem.nama
        holder.alat.text = currentitem.alat
        holder.jumlah.text = currentitem.jumlah
        holder.tanggal_pinjam.text = currentitem.tanggal_pinjam
        holder.tanggal_kembali.text = currentitem.tanggal_kembali
        holder.status_peminjaman.text = currentitem.status_peminjaman
    }

    override fun getItemCount(): Int {
        return peminjamanList.size
    }

    class PeminjamanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var nama: TextView = itemView.findViewById(R.id.namaListPinjamId)
        var alat: TextView = itemView.findViewById(R.id.alatListPinjamId)
        var jumlah: TextView = itemView.findViewById(R.id.jumlahListPinjamId)
        var tanggal_pinjam: TextView = itemView.findViewById(R.id.tglPinjamListPinjamId)
        var tanggal_kembali: TextView = itemView.findViewById(R.id.tglKembaliListPinjamId)
        var status_peminjaman: TextView = itemView.findViewById(R.id.statusListPinjamId)
    }
}