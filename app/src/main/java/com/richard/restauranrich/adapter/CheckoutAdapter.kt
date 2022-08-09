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
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutAdapter (var context: Context?, var list: ArrayList<CheckoutModel>) :
RecyclerView.Adapter<CheckoutAdapter.DataCheckout>() {

    private var saatItemDiklik: ((CheckoutModel) -> Unit)? = null

    class DataCheckout(view: View) : RecyclerView.ViewHolder(view) {
        var tvNama = view.findViewById<TextView>(R.id.Tv_NamaChekout)
        var tvHarga = view.findViewById<TextView>(R.id.Tv_HargaCheckout)
        var TvJumlah = view.findViewById<TextView>(R.id.Tv_JumlahOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataCheckout {
        return DataCheckout(
            LayoutInflater.from(parent.context).inflate(R.layout.list_data_checkout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataCheckout, position: Int) {
        val item = list[position]

        holder.tvNama.text = item.nama
        holder.tvHarga.text = formatRupiah(item.harga!!.toDouble())
        holder.TvJumlah.text = "x${item.jumlah}"

        holder.itemView.setOnClickListener { saatItemDiklik?.invoke(item) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun formatRupiah(number: Double): String {
        val localID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        return formatRupiah.format(number)
    }

    fun aturSaatitemDiklik(panggilBalik: (CheckoutModel) -> Unit) {
        this.saatItemDiklik = panggilBalik
    }

    @SuppressLint("NotifyDataSetChanged")
    fun tambahData(item: ArrayList<CheckoutModel>) {
        this.list = item
        notifyDataSetChanged()
    }
}