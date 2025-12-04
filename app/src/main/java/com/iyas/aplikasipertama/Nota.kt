package com.iyas.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class Nota : AppCompatActivity() {

    // 1. Inisialisasi TextView
    private lateinit var tvNamaPemesan : TextView
    private lateinit var tvPesananMie : TextView
    private lateinit var tvPesananRoti : TextView
    private lateinit var tvPesananKopi : TextView
    private lateinit var tvTotalHarga : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota) // Pastikan nama layout adalah nota.xml

        // 2. Deklarasi View ID (sesuai ID di XML nota.xml)
        tvNamaPemesan = findViewById(R.id.tvNamaPemesan)
        tvPesananMie = findViewById(R.id.tvPesananMie)
        tvPesananRoti = findViewById(R.id.tvPesananRoti)
        tvPesananKopi = findViewById(R.id.tvPesananKopi)
        tvTotalHarga = findViewById(R.id.tvTotalHarga)

        // 3. Terima Data dari Intent
        val nama = intent.getStringExtra("nama_pemesan")
        val mieDetail = intent.getStringExtra("mie_detail")
        val rotiDetail = intent.getStringExtra("roti_detail")
        val kopiDetail = intent.getStringExtra("kopi_detail")
        val total = intent.getIntExtra("total_harga", 0) // Default 0

        // 4. Format Total Harga ke mata uang Rupiah
        val formatRupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val totalRupiah = formatRupiah.format(total.toLong())

        // 5. Set teks ke TextView
        tvNamaPemesan.text = "Nama Pemesan : $nama"

        // Menggunakan format yang diminta: Pesanan X (Nama Menu): [Detail Pesanan/Tanda Minus]
        tvPesananMie.text = "Pesanan 1 (Mie-ras): $mieDetail"
        tvPesananRoti.text = "Pesanan 2 (Roti kobong): $rotiDetail"
        tvPesananKopi.text = "Pesanan 3 (Caffeine Rush): $kopiDetail"

        tvTotalHarga.text = "Total Pembayaran : $totalRupiah"
    }
}