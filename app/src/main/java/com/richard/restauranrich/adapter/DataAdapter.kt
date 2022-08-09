package com.richard.restauranrich.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.richard.restauranrich.R
import com.richard.restauranrich.menu.MenuDetailActivity
import com.richard.restauranrich.model.DataModel

class DataAdapter(var context: Context?, var list: ArrayList<DataModel>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama = itemView.findViewById<TextView>(R.id.Tv_NamaItem)
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_data_fragment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = list[position]

        Glide.with(context!!).load(result.gambar)
            .apply(RequestOptions().override(150, 150))
            .into(holder.imageView)

        holder.tvNama.text = result.nama

        holder.itemView.setOnClickListener {
            val pindah = Intent(context!!, MenuDetailActivity::class.java)
            pindah.putExtra(MenuDetailActivity.EXTRA_NAMA, result.nama)
            pindah.putExtra(MenuDetailActivity.EXTRA_KATEGORI, result.kategori)
            pindah.putExtra(MenuDetailActivity.EXTRA_HARGA, result.harga)
            pindah.putExtra(MenuDetailActivity.EXTRA_IMG, result.gambar)
            pindah.putExtra(MenuDetailActivity.EXTRA_DESKRIPSI, result.deskripsi)
            context!!.startActivity(pindah)

            Toast.makeText(context!!, list[position].nama, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}