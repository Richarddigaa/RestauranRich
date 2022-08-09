package com.richard.restauranrich.pesanan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.richard.restauranrich.databinding.ActivityNomorMejaBinding

class NomorMejaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNomorMejaBinding

    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNomorMejaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Nomor Meja"

        binding.apply {
            BtnLanjut.setOnClickListener {
                sharedPref = getSharedPreferences("MyShared", Context.MODE_PRIVATE)
                val nomer = EtNomerMeja.text.toString()
                if (nomer.isNotEmpty()) {
                    val editor: SharedPreferences.Editor = sharedPref.edit()
                    editor.putString("nomor_meja", nomer)
                    editor.apply()
                    startActivity(Intent(this@NomorMejaActivity, CheckoutActivity::class.java))
                } else {
                    Toast.makeText(this@NomorMejaActivity, "Isi nomor meja dahulu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}