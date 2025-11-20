package com.iyas.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilFormActivity2 : AppCompatActivity() {

    // 1. inisialisasi jenis dan type parameter + buat variabel

    private lateinit var tvNama : TextView
    private lateinit var tvAlamat : TextView
    private lateinit var tvNomorHP : TextView
    private lateinit var tvAgama : TextView
    private lateinit var tvKelamin : TextView
    private lateinit var tvHobi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form2)

        //2. Deklarasi hasil inisialisasi tadi menjadi Value id
        // tvNama depan disamakan dengan parameter atas
        // tvNama belakang disamakan dengan id di file xml form pendaftaran
        tvNama = findViewById(R.id.tvNama)
        tvAlamat = findViewById(R.id.tvAlamat)
        tvNomorHP = findViewById(R.id.tvNomor)
        tvAgama = findViewById(R.id.tvAgama)
        tvKelamin = findViewById(R.id.tvKelamin)
        tvHobi = findViewById(R.id.tvHobi)



        //3.  Inisialisasi var baru dengan intent sesuai variable activiye sebelumnya
        // nama depan namakan terserah
        // nama di belakang sama seperti string variabel di form pendaftaran
        val Nama = intent.getStringExtra("nama")
        val Alamat = intent.getStringExtra("alamat")
        val NoHP = intent.getStringExtra( "nohp")
        val Agama = intent.getStringExtra( "agama")
        val JenisKelamin = intent.getStringExtra( "kelamin")
        val Hobi = intent.getStringExtra( "hobi")


        //4.  Gunakan Init Awal.text llau gunakan $ untuk manipulasi string
        // tvNama sama dengan lateinit langkah 1
        // Nama hijau terserah
        // Nama$ sama dengan variabel langkah 3

        tvNama.text = "Nama : $Nama"
        tvAlamat.text = "Alamat : $Alamat"
        tvNomorHP.text = "NoHP : $NoHP"
        tvAgama.text = "Agama : $Agama"
        tvKelamin.text ="Kelamin : $JenisKelamin"
        tvHobi.text ="Hobi : $Hobi"



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}