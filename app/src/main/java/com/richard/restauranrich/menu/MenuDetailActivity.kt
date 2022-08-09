package com.richard.restauranrich.menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.richard.restauranrich.database.SQLite
import com.richard.restauranrich.databinding.ActivityMenuDetailBinding
import com.richard.restauranrich.model.CheckoutModel
import com.richard.restauranrich.pesanan.CheckoutActivity
import com.richard.restauranrich.pesanan.NomorMejaActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MenuDetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_KATEGORI = "kategori"
        const val EXTRA_NAMA = "nama"
        const val EXTRA_DESKRIPSI = "deskripsi"
        const val EXTRA_IMG = "gambar"
        const val EXTRA_HARGA = "harga"
    }

    private lateinit var binding: ActivityMenuDetailBinding

    private lateinit var sqlite: SQLite
    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sqlite = SQLite(this)

        val kategori = intent.getStringExtra(EXTRA_KATEGORI)
        val nama = intent.getStringExtra(EXTRA_NAMA)
        val harga = intent.getIntExtra(EXTRA_HARGA, 0)
        val gambar = intent.getIntExtra(EXTRA_IMG, 0)
        val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)

        supportActionBar!!.title = nama

        binding.apply {
            TvDeskripsiMenu.text = deskripsi
            TvKategori.text = kategori
            TvNamaMenu.text = nama
            TvHargaMenu.text = formatRupiah(harga.toDouble())

            Glide.with(this@MenuDetailActivity).load(gambar)
                .apply(RequestOptions().override(200, 200))
                .into(ImgMenu)

            var jumlah = TvJumlah.text.toString().toInt()
            BtnTambah.setOnClickListener {
                jumlah++
                TvJumlah.text = jumlah.toString()
                TvTotal.text = formatRupiah(jumlah * harga.toDouble())
            }

            BtnKurang.setOnClickListener {
                if (jumlah > 0) {
                    jumlah--
                    TvJumlah.text = jumlah.toString()
                    TvTotal.text = formatRupiah(jumlah * harga.toDouble())
                }
            }
            BtnPesan.setOnClickListener {
                if (jumlah > 0){
                    sharedPref = getSharedPreferences("MyShared", Context.MODE_PRIVATE)
                    var nomor_meja = sharedPref.getString("nomor_meja", "")

                    val calendar = Calendar.getInstance()
                    val format = SimpleDateFormat(" d/MM/yyyy  HH:mm:ss ")
                    val time: String = format.format(calendar.time)

                    if (nomor_meja!!.isEmpty()){
                        val buatDialog = AlertDialog.Builder(this@MenuDetailActivity)
                        buatDialog.setMessage("Silahkan isi nomor meja dahulu")
                        buatDialog.setCancelable(true)
                        buatDialog.setPositiveButton("Ya"){ dialog, _ ->
                            startActivity(Intent(this@MenuDetailActivity, NomorMejaActivity::class.java))
                            dialog.dismiss()
                        }
                        buatDialog.setNegativeButton("Tidak"){ dialog, _ ->
                            dialog.dismiss()
                        }
                        val pemberitahuan = buatDialog.create()
                        pemberitahuan.show()
                    } else {
                        val cm = CheckoutModel(
                            nama = nama,
                            harga = harga * jumlah,
                            jumlah = jumlah.toString().toInt(),
                            nomor_meja = nomor_meja.toString().toInt(),
                            time = time
                        )
                        val status = sqlite.checkout(cm)
                        if (status > -1){
                            Toast.makeText(this@MenuDetailActivity, "Order berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@MenuDetailActivity, CheckoutActivity::class.java))
                        } else{
                            Toast.makeText(this@MenuDetailActivity, "Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@MenuDetailActivity, "Pesan menu minimal 1", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun formatRupiah (number : Double): String {
        val localID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        return formatRupiah.format(number)
    }
}