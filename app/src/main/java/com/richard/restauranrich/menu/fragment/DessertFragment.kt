package com.richard.restauranrich.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.richard.restauranrich.R
import com.richard.restauranrich.adapter.DataAdapter
import com.richard.restauranrich.database.DataMenu
import com.richard.restauranrich.model.DataModel

class DessertFragment : Fragment() {

    private lateinit var rv_list : RecyclerView
    private lateinit var list : ArrayList<DataModel>
    private lateinit var myAdapter : DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dessert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = arrayListOf()
        rv_list = view.findViewById(R.id.Rv_Dessert)

        myAdapter = DataAdapter(this.context, list)

        list.addAll(DataMenu.listDessert)

        rv_list.layoutManager = GridLayoutManager(this.context, 2)
        rv_list.setHasFixedSize(true)
        rv_list.adapter = myAdapter
    }
}