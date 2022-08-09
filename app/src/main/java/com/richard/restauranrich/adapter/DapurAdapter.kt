package com.richard.restauranrich.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.richard.restauranrich.R
import com.richard.restauranrich.model.CheckoutModel

class DapurAdapter (var context: Context?, var list: ArrayList<CheckoutModel>) :
RecyclerView.Adapter<DapurAdapter.DataDapur>() {

    private var saatItemDiklik: ((CheckoutModel) -> Unit)? = null

    class DataDapur(view: View) : RecyclerView.ViewHolder(view) {
        var tvNama = view.findViewById<TextView>(R.id.Tv_NamaBarang)
        var TvJumlah = view.findViewById<TextView>(R.id.Tv_JumlahBarang)
        var TvTime = view.findViewById<TextView>(R.id.Tv_Time)
        var TvNomor = view.findViewById<TextView>(R.id.Tv_Nomor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataDapur {
        return DataDapur(
            LayoutInflater.from(parent.context).inflate(R.layout.list_data_dapur, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataDapur, position: Int) {
        val item = list[position]

        holder.tvNama.text = item.nama
        holder.TvNomor.text = item.nomor_meja.toString()
        holder.TvJumlah.text ="x${item.jumlah}"
        holder.TvTime.text = item.time

        holder.itemView.setOnClickListener { saatItemDiklik?.invoke(item) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun aturSaatitemDiklik(panggilBalik: (CheckoutModel) -> Unit) {
        this.saatItemDiklik = panggilBalik
    }

    @SuppressLint("NotifyDataSetChanged")
    fun tambahData(item : ArrayList<CheckoutModel>) {
        this.list = item
        notifyDataSetChanged()
    }
}