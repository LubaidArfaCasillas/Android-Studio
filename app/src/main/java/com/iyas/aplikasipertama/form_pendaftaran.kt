package com.iyas.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class form_pendaftaran : AppCompatActivity() {

    //langkah 1 membuat variabel
    lateinit var  etNama : EditText
    lateinit var  etAlamat : EditText
    lateinit var  etNomor : EditText
    lateinit var  spAgama : Spinner
    lateinit var  rgKelamin : RadioGroup
    lateinit var cbMembaca : CheckBox
    lateinit var cbTidur : CheckBox
    lateinit var cbBelajar : CheckBox
    lateinit var cbBermain : CheckBox
    lateinit var  btSimpan : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.form_pendaftaran)

        //langkah 4 memanggil fun init
        init()

        //langkah 5 menambahkan click listener pada button
        btSimpan.setOnClickListener{

            //langkah 6 Mengambil data dari form
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val nohp = etNomor.text.toString()
            val agama = spAgama.selectedItem.toString()        // fungsi toString() untuk mengubah value ke string

            val selectedRadioId = rgKelamin.checkedRadioButtonId
            val kelamin = if (selectedRadioId != -1) {
                val radioGroup = findViewById<RadioButton>(selectedRadioId)
                radioGroup.text.toString()
            }else{

                ""
            }



            val hobilist = mutableListOf<String>()
            if (cbMembaca.isChecked) hobilist.add("Membaca")
            if (cbTidur.isChecked) hobilist.add("Tidur")
            if (cbBermain.isChecked) hobilist.add("Bermain")
            if (cbBelajar.isChecked) hobilist.add("Belajar")
            val hobi = if (hobilist.isNotEmpty()){
              hobilist.joinToString( ", ")
            } else {
                "Tidak ada hobi"
            }


            //langkah 7 berpinda ke activity hasil form
            val keHasil = Intent(this,HasilFormActivity2::class.java)

            //langkah 8 menyertakan data untuk dikirim
            // hijau = string variabel
            keHasil.putExtra( "nama", nama)
            keHasil.putExtra( "alamat", alamat)
            keHasil.putExtra( "nohp", nohp)
            keHasil.putExtra( "agama",agama)
            keHasil.putExtra( "kelamin", kelamin)
            keHasil.putExtra("hobi",hobi)

            //langka 9 jangan lupa start activity
            startActivity(keHasil)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //langkah 2 membuat fun init
    fun init(){

        //langkah 3 mengisikan variabel
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etNomor = findViewById(R.id.etNoHP)
        spAgama = findViewById(R.id.spAgama)
        rgKelamin = findViewById(R.id.rgKelamin)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbTidur = findViewById(R.id.cbTidur)
        cbBelajar = findViewById(R.id.cbBelajar)
        cbBermain = findViewById(R.id.cbBermain)
        btSimpan = findViewById(R.id.btSimpan)

    }

}