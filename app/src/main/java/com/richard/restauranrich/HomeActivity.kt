package com.richard.restauranrich

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.richard.restauranrich.dapur.DapurActivity
import com.richard.restauranrich.databinding.ActivityHomeBinding
import com.richard.restauranrich.menu.MenuActivity
import com.richard.restauranrich.pesanan.NomorMejaActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            BtnMenu.setOnClickListener {
                startActivity(Intent(this@HomeActivity, MenuActivity::class.java))
            }
            BtnPesanan.setOnClickListener {
                startActivity(Intent(this@HomeActivity, NomorMejaActivity::class.java))
            }
            BtnDapur.setOnClickListener {
                startActivity(Intent(this@HomeActivity, DapurActivity::class.java))
            }
        }
    }
}