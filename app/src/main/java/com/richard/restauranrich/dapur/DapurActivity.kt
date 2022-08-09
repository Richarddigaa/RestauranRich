package com.richard.restauranrich.dapur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.richard.restauranrich.adapter.DapurAdapter
import com.richard.restauranrich.database.SQLite
import com.richard.restauranrich.databinding.ActivityDapurBinding
import com.richard.restauranrich.model.CheckoutModel

class DapurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDapurBinding

    private lateinit var sqlite : SQLite
    private lateinit var listDapur : ArrayList<CheckoutModel>
    private lateinit var myAdapter: DapurAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "List Order"

        listDapur = arrayListOf()
        myAdapter = DapurAdapter(this, listDapur)
        sqlite = SQLite(this)

        lihatData()

        binding.apply {
            RvDapur.adapter = myAdapter
            RvDapur.layoutManager = LinearLayoutManager(this@DapurActivity)
            RvDapur.setHasFixedSize(true)
        }

        myAdapter.aturSaatitemDiklik { hapusDapur(it.id!!) }
    }

    private fun lihatData() {
        val cm = sqlite.ambilData()
        myAdapter.tambahData(cm)
    }

    private fun hapusDapur(no_index : Int){
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