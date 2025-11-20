package com.iyas.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {
    //langkah 1
    //membat variabel
    lateinit var card_Home : CardView
    lateinit var card_Bantuan : CardView
    lateinit var card_Pegawai : CardView
    lateinit var card_Galeri : CardView
    lateinit var card_Survei : CardView
    lateinit var card_Keluar : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)
        //langkah 4
        //memanggil fun init()
        init()



        //langkah 5
        //menambahkan kode onClick pada cardView

        card_Home.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Home Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity,form_pendaftaran::class.java)
            intent.putExtra("Nama", "Iyas")
            intent.putExtra("Alamat", "Sukoharjo")
            startActivity(intent)
        }
        card_Bantuan.setOnClickListener {
            Toast.makeText(this@CardActivity, "Card View Bantuan Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity,kalkulator::class.java)
            intent.putExtra("Nama", "Iyas")
            intent.putExtra("Alamat", "Sukoharjo")
            startActivity(intent)
        }
        card_Pegawai.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Pegawai Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent (this@CardActivity, RumahMakan::class.java)
            startActivity(intent)
        }
        card_Galeri.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Galeri Diklik", Toast.LENGTH_SHORT).show()
        }
        card_Survei.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Survei Diklik", Toast.LENGTH_SHORT).show()
        }
        card_Keluar.setOnClickListener {
            Toast.makeText(this@CardActivity, "Card View Bantuan Diklik", Toast.LENGTH_SHORT).show()
        }








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //langkah 2
    //membuat fun baru
    fun init(){

        //langkah 3
        //mengisikan variabel
        card_Home = findViewById(R.id.cvBeranda)
        card_Bantuan = findViewById(R.id.cvBantuan)
        card_Pegawai = findViewById(R.id.cvPegawai)
        card_Galeri = findViewById(R.id.cvGaleri)
        card_Survei = findViewById(R.id.cvSurvei)
        card_Keluar = findViewById(R.id.cvKeluar)


    }
}