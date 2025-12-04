package com.iyas.aplikasipertama

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.switchmaterial.SwitchMaterial

class konversiSuhu : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konversi_suhu)

        val spinnerFrom: Spinner = findViewById(R.id.spSuhu1)
        val spinnerTo: Spinner = findViewById(R.id.spSuhu2)
        val input: EditText = findViewById(R.id.etSuhu1)
        val output: TextView = findViewById(R.id.tvSuhu2)
        val button: Button = findViewById(R.id.btnHitung)

        var hasil = 0.0

        button.setOnClickListener {

            val suhuAwal = spinnerFrom.selectedItem.toString()
            val suhuAkhir = spinnerTo.selectedItem.toString()
            val angkaSuhuAwal = input.text.toString().toDoubleOrNull()

            if (angkaSuhuAwal != null) {

                if (suhuAwal == "Celsius") {
                    if (suhuAkhir == "Kelvin") {
                        hasil = angkaSuhuAwal + 273.15
                    }
                    if (suhuAkhir == "Fahrenhait") {
                        hasil = (angkaSuhuAwal * 1.8) + 32
                    }
                    if (suhuAkhir == "Celsius") {
                        hasil = angkaSuhuAwal
                    }
                    output.text = hasil.toString()
                }

                if (suhuAwal == "Fahrenhait") {
                    if (suhuAkhir == "Celsius") {
                        hasil = (angkaSuhuAwal - 32 ) / 1.8
                    }
                    if (suhuAkhir == "Fahrenhait") {
                        hasil = angkaSuhuAwal
                    }
                    if (suhuAkhir == "Kelvin") {
                        hasil = angkaSuhuAwal - 32 / 1.8 + 273.15
                    }
                    output.text = hasil.toString()
                }

                if (suhuAwal == "Kelvin") {
                    if (suhuAkhir == "Kelvin") {
                        hasil = angkaSuhuAwal
                    }
                    if (suhuAkhir == "Fahrenhait") {
                        hasil = (angkaSuhuAwal - 273.15) * 1.8 + 32
                    }
                    if (suhuAkhir == "Celsius") {
                        hasil = angkaSuhuAwal - 273.15
                    }
                    output.text = hasil.toString()
                }

            } else {
                input.error = "Masukkan Angka nya Dulu ya"

                Handler(Looper.getMainLooper()).postDelayed({
                    input.error = null
                }, 2000)
            }
        }
    }

}