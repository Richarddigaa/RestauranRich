package com.richard.restauranrich.database

import com.richard.restauranrich.R
import com.richard.restauranrich.model.DataModel

object DataMenu {
    private val kategori = arrayOf("MAKANAN", "MINUMAN", "DESSERT")

    private val namaMakanan = arrayOf(
        "Nasi Goreng", "Bakso", "Mie Ayam", "Sate Ayam"
    )

    private val hargaMakanan = arrayOf(
        12_000, 13_000, 12_000, 15_000
    )

    private val gambarMakanan = arrayOf(
        R.drawable.nasi_goreng, R.drawable.bakso, R.drawable.mie_ayam, R.drawable.sate_ayam
    )

    private val deskripsiMakanan = arrayOf(
        "Nasi goreng adalah sebuah makanan berupa nasi yang digoreng dan diaduk dalam minyak goreng, margarin, atau mentega.",
        "Bakso umumnya dibuat dari campuran daging sapi giling dan tepung tapioka, tetapi ada juga bakso yang terbuat dari daging ayam, ikan, atau udang bahkan daging kerbau.",
        "Mi ayam adalah hidangan khas Indonesia yang terbuat dari mi gandum kuning yang dibumbui dengan daging ayam yang biasanya dipotong dadu.",
        "Pada umumnya sate ayam dimasak dengan cara dipanggang dengan menggunakan arang dan disajikan dengan pilihan bumbu kacang atau bumbu kecap."
    )

    val listMakanan : ArrayList<DataModel>
        get() {
            val makanan = arrayListOf<DataModel>()
            for (position in namaMakanan.indices){
                val makan = DataModel()
                makan.kategori = kategori[0]
                makan.nama = namaMakanan[position]
                makan.harga = hargaMakanan[position]
                makan.gambar = gambarMakanan[position]
                makan.deskripsi = deskripsiMakanan[position]
                makanan.add(makan)
            }
            return makanan
        }

    private val namaMinuman = arrayOf(
        "Es Teh", "Es Jeruk", "Jus Alpukat", "Jus Mangga"
    )

    private val hargaMinuman = arrayOf(
        5_000, 6_000, 10_000, 10_000
    )

    private val gambarMinuman = arrayOf(
        R.drawable.es_teh, R.drawable.es_jeruk, R.drawable.jus_alpukat, R.drawable.jus_mangga
    )

    private val deskripsiMinuman = arrayOf(
        "Es teh adalah minuman yang sering diminum saat siang hari karena suhu udara yang panas.",
        "Biasanya minuman ini mengandung sedikit atau sari buah jeruk.",
        "Biasanya minuman ini mengandung sedikit atau sari buah alpukat.",
        "Biasanya minuman ini mengandung sedikit atau sari buah mangga."
    )

    val listMinuman : ArrayList<DataModel>
        get() {
            val minuman = arrayListOf<DataModel>()
            for (position in namaMinuman.indices){
                val minum = DataModel()
                minum.kategori = kategori[1]
                minum.nama = namaMinuman[position]
                minum.harga = hargaMinuman[position]
                minum.gambar = gambarMinuman[position]
                minum.deskripsi = deskripsiMinuman[position]
                minuman.add(minum)
            }
            return minuman
        }

    private val namaDessert = arrayOf(
        "Kue Putu", "Onde-Onde"
    )

    private val hargaDessert = arrayOf(
        5_000, 5_000
    )

    private val gambarDessert = arrayOf(
        R.drawable.kue_putu, R.drawable.onde_onde
    )

    private val deskripsiDessert = arrayOf(
        "Kue putu adalah jenis kudapan tradisional Indonesia berupa kue dengan isian gula jawa, dibalut dengan parutan kelapa, dan tepung beras butiran kasar.",
        "Onde-onde terbuat dari tepung terigu ataupun tepung ketan yang digoreng atau direbus dan permukaannya ditaburi/dibalur dengan biji wijen."
    )

    val listDessert : ArrayList<DataModel>
        get() {
            val dessert = arrayListOf<DataModel>()
            for (position in namaDessert.indices){
                val dess = DataModel()
                dess.kategori = kategori[2]
                dess.nama = namaDessert[position]
                dess.harga = hargaDessert[position]
                dess.gambar = gambarDessert[position]
                dess.deskripsi = deskripsiDessert[position]
                dessert.add(dess)
            }
            return dessert
        }
}