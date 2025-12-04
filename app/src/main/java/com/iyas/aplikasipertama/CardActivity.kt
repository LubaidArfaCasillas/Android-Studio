package com.iyas.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog // <-- Import untuk AlertDialog

class CardActivity : AppCompatActivity() {
    //langkah 1
    //membuat variabel
    lateinit var card_Form : CardView
    lateinit var card_RumahMakan : CardView
    lateinit var card_Kalkulator : CardView
    lateinit var card_KonversiSuhu : CardView
    lateinit var card_Profile : CardView
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

        card_Form.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Home Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity,form_pendaftaran::class.java)
            intent.putExtra("Nama", "Iyas")
            intent.putExtra("Alamat", "Sukoharjo")
            startActivity(intent)
        }
        card_RumahMakan.setOnClickListener {
            Toast.makeText(this@CardActivity, "Card View Bantuan Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@CardActivity,RumahMakan::class.java)
            intent.putExtra("Nama", "Iyas")
            intent.putExtra("Alamat", "Sukoharjo")
            startActivity(intent)
        }
        card_Kalkulator.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Pegawai Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent (this@CardActivity, kalkulator::class.java)
            startActivity(intent)
        }
        card_KonversiSuhu.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Galeri Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent (this@CardActivity, konversiSuhu::class.java)
            startActivity(intent)
        }
        card_Profile.setOnClickListener {
            Toast.makeText( this@CardActivity, "Card View Survei Diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent (this@CardActivity, profile::class.java)
            startActivity(intent)
        }

        // LOGIKA POP-UP KELUAR
        card_Keluar.setOnClickListener {
            // Memanggil fungsi pop-up konfirmasi saat card_Keluar diklik
            showExitConfirmationDialog()
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
        card_Form = findViewById(R.id.cvForm)
        card_RumahMakan = findViewById(R.id.cvRumahMakan)
        card_Kalkulator = findViewById(R.id.cvKalkulator)
        card_KonversiSuhu = findViewById(R.id.cvKonversiSuhu)
        card_Profile = findViewById(R.id.cvProfile)
        card_Keluar = findViewById(R.id.cvKeluar)
    }

    // FUNGSI UNTUK MENAMPILKAN ALERT DIALOG (POP-UP)
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Konfirmasi Keluar")
        builder.setMessage("Yakin ingin keluar dari aplikasi?")

        // Tombol Ya/Positive Button
        builder.setPositiveButton("Ya") { dialog, which ->
            finish() // Menutup Activity dan keluar dari aplikasi
        }

        // Tombol Tidak/Negative Button
        builder.setNegativeButton("Tidak") { dialog, which ->
            dialog.dismiss() // Hanya menutup pop-up
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}