package com.richard.restauranrich.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.richard.restauranrich.model.CheckoutModel

class SQLite(context: Context) : SQLiteOpenHelper(context, NAMA_DATABASE, null, VERSI_DATABASE) {

    companion object {
        private const val NAMA_DATABASE = "ujk"
        private const val VERSI_DATABASE = 1

        private const val NAMA_TABLE = "pesanan"
        private const val KOLOM_ID = "id"
        private const val KOLOM_NAMA = "nama"
        private const val KOLOM_HARGA = "harga"
        private const val KOLOM_JUMLAH = "jumlah"
        private const val KOLOM_NOMOR = "nomor"
        private const val KOLOM_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val tabelPesanan = ("CREATE TABLE $NAMA_TABLE (" +
                "$KOLOM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$KOLOM_NAMA TEXT, " +
                "$KOLOM_HARGA INTEGER, " +
                "$KOLOM_NOMOR INTEGER, " +
                "$KOLOM_JUMLAH INTEGER, " +
                "$KOLOM_TIME TEXT " +
                ")")
        db?.execSQL(tabelPesanan)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE")
    }

    fun checkout (cm : CheckoutModel) : Long {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(KOLOM_ID, cm.id)
        cv.put(KOLOM_NAMA, cm.nama)
        cv.put(KOLOM_HARGA, cm.harga)
        cv.put(KOLOM_JUMLAH, cm.jumlah)
        cv.put(KOLOM_NOMOR, cm.nomor_meja)
        cv.put(KOLOM_TIME, cm.time)

        val berhasil = db.insert(NAMA_TABLE, null, cv)
        db.close()

        return berhasil
    }

    @SuppressLint("Range", "Recycle")
    fun ambilCheckout (nomor_meja: Int) : ArrayList<CheckoutModel>{
        val dataCheckout : ArrayList<CheckoutModel> = ArrayList()
        val queryTampil = "SELECT * FROM $NAMA_TABLE WHERE $KOLOM_NOMOR = $nomor_meja"
        val db = this.readableDatabase
        val kursor : Cursor

        try {
            kursor = db.rawQuery(queryTampil, null)
        } catch (kesalahan : Exception) {
            kesalahan.printStackTrace()
            db.execSQL(queryTampil)
            return ArrayList()
        }

        var id : Int?
        var nama : String?
        var harga : Int?
        var jumlah : Int?
        var nomor_meja : Int?
        var time : String?

        if (kursor.moveToFirst()){
            do {
                id = kursor.getInt(kursor.getColumnIndex("id"))
                nama = kursor.getString(kursor.getColumnIndex("nama"))
                harga = kursor.getInt(kursor.getColumnIndex("harga"))
                jumlah = kursor.getInt(kursor.getColumnIndex("jumlah"))
                nomor_meja = kursor.getInt(kursor.getColumnIndex("nomor"))
                time = kursor.getString(kursor.getColumnIndex("time"))

                val cm = CheckoutModel(id, nama, harga, jumlah, nomor_meja, time)
                dataCheckout.add(cm)
            } while (kursor.moveToNext())
        }
        return dataCheckout
    }

    fun hapus(no_index : Int) : Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(KOLOM_ID, no_index)
        val berhasil = db.delete(NAMA_TABLE, "id=$no_index", null)
        db.close()
        return berhasil
    }

    @SuppressLint("Range")
    fun ambilData() : ArrayList<CheckoutModel>{
        val dataDapur : ArrayList<CheckoutModel> = ArrayList()
        val queryTampil = "SELECT * FROM $NAMA_TABLE"
        val db = this.readableDatabase
        val kursor : Cursor

        try {
            kursor = db.rawQuery(queryTampil, null)
        } catch (kesalahan : Exception) {
            kesalahan.printStackTrace()
            db.execSQL(queryTampil)
            return ArrayList()
        }

        var id : Int?
        var nama: String?
        var harga: Int?
        var jumlah : Int?
        var nomor_meja: Int?
        var time : String?

        if (kursor.moveToFirst()){
            do {
                id = kursor.getInt(kursor.getColumnIndex("id"))
                nama = kursor.getString(kursor.getColumnIndex("nama"))
                harga = kursor.getInt(kursor.getColumnIndex("harga"))
                jumlah = kursor.getInt(kursor.getColumnIndex("jumlah"))
                nomor_meja = kursor.getInt(kursor.getColumnIndex("nomor"))
                time = kursor.getString(kursor.getColumnIndex("time"))

                val cm = CheckoutModel(id, nama, harga, jumlah, nomor_meja, time)
                dataDapur.add(cm)
            } while (kursor.moveToNext())
        }
        return dataDapur
    }
}