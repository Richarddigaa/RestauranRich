package com.richard.restauranrich.menu

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.richard.restauranrich.adapter.DataAdapter
import com.richard.restauranrich.databinding.ActivityMenuBinding
import com.richard.restauranrich.menu.fragment.FragmentAdapter
import com.richard.restauranrich.model.DataModel

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    private lateinit var adapter: DataAdapter
    private lateinit var arrayList: ArrayList<DataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "List Menu"

        arrayList = arrayListOf()
        adapter = DataAdapter(this, arrayList)

        binding.apply {
            viewpager.adapter = FragmentAdapter(this@MenuActivity)
            TabLayoutMediator(tabLayout, viewpager){ tab, index ->
                tab.text = when(index){
                    0 -> "MAKANAN"
                    1 -> "MINUMAN"
                    2 -> "DESSERT"
                    else -> throw Resources.NotFoundException("Position Not Found")
                }
            }.attach()
        }
    }
}