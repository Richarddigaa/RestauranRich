package com.richard.restauranrich.model

data class CheckoutModel(
    var id: Int? = null,
    var nama: String? = null,
    var harga: Int? = null,
    var jumlah: Int? = null,
    var nomor_meja: Int? = null,
    var time: String? = null
)