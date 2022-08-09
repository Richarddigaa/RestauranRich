package com.richard.restauranrich.pesanan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.richard.restauranrich.adapter.CheckoutAdapter
import com.richard.restauranrich.dapur.DapurActivity
import com.richard.restauranrich.database.SQLite
import com.richard.restauranrich.databinding.ActivityCheckoutBinding
import com.richard.restauranrich.menu.MenuActivity
import com.richard.restauranrich.model.CheckoutModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    private lateinit var sharedPref : SharedPreferences
    private lateinit var sqlite : SQLite
    private lateinit var listCheckout : ArrayList<CheckoutModel>
    private lateinit var myAdapter: CheckoutAdapter

    private lateinit var nomor_meja: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "List Order"

        listCheckout = arrayListOf()
        myAdapter = CheckoutAdapter(this, listCheckout)
        sqlite = SQLite(this)

        sharedPref = getSharedPreferences("MyShared", Context.MODE_PRIVATE)
        nomor_meja = sharedPref.getString("nomor_meja", "").toString()

        binding.apply {
            TvNomorMeja.text = "Nomor Meja : $nomor_meja"

            lihatData()

            RvCheckout.adapter = myAdapter
            RvCheckout.layoutManager = LinearLayoutManager(this@CheckoutActivity)
            RvCheckout.setHasFixedSize(true)

            myAdapter.aturSaatitemDiklik { hapusCheckout(it.id!!) }

            BtnTambah.setOnClickListener {
                startActivity(Intent(this@CheckoutActivity, MenuActivity::class.java))
            }
            BtnKirim.setOnClickListener {
                startActivity(Intent(this@CheckoutActivity, DapurActivity::class.java))
                Toast.makeText(this@CheckoutActivity, "Berhasil terkirim", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun lihatData(){
        val cm = sqlite.ambilCheckout(nomor_meja.toInt())
        myAdapter.tambahData(cm)

        var total = 0
        for (item in myAdapter.list) {
            total += item.harga!!
        }
        binding.TvTotalHarga.text = formatRupiah(total.toDouble())
    }

    private fun formatRupiah (number : Double): String {
        val localID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        return formatRupiah.format(number)
    }

    private fun hapusCheckout(no_index: Int){
        val buatDialog = AlertDialog.Builder(this)
        buatDialog.setMessage("Anda yakin ingin menghapus?")
        buatDialog.setCancelable(true)
        buatDialog.setPositiveButton("Ya"){ dialog, _ ->
            sqlite.hapus(no_index)
            lihatData()
            dialog.dismiss()
        }
        buatDialog.setNegativeButton("Tidak"){ dialog, _ ->
            dialog.dismiss()
        }
        val pemberitahuan = buatDialog.create()
        pemberitahuan.show()
    }
}