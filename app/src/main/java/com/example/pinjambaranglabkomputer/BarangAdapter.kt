package com.example.pinjambaranglabkomputer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BarangAdapter(private val barangList: ArrayList<ListBarang>): RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BarangViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listbarang_layout, parent,false)
        return BarangViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val currentitem = barangList[position]

        holder.nama.text = currentitem.nama
        holder.jumlah.text = currentitem.jumlah.toString()
    }

    override fun getItemCount(): Int {
        return barangList.size
    }

    class BarangViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var nama: TextView  = itemView.findViewById(R.id.nameBarangId)
        var jumlah: TextView  = itemView.findViewById(R.id.jumlahBarangId)
    }

}