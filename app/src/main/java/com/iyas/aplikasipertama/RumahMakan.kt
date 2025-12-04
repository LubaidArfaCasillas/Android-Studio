package com.iyas.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale

class RumahMakan : AppCompatActivity() {

    // Harga Konstan
    private val HARGA_MIE = 10000
    private val HARGA_ROTI = 7000
    private val HARGA_KOPI = 9000

    // Deklarasi Variabel
    private lateinit var etNamaPemesan : TextInputEditText
    private lateinit var etMie : EditText
    private lateinit var etRoti : EditText
    private lateinit var etKopi : EditText
    private lateinit var btPesan : Button

    // Fungsi formatRupiah ditempatkan di dalam class untuk menghindari error scope
    private fun formatRupiah(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        // Menghapus simbol mata uang Rp dan koma desimal, hanya menyisakan angka
        return format.format(number.toLong()).replace("Rp", "").replace(",00", "").trim()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumah_makan)

        init()

        btPesan.setOnClickListener{
            // 1. Ambil Nama Pemesan
            val namaPemesan = etNamaPemesan.text.toString()

            // 2. Ambil Jumlah dan Hitung Subtotal
            val jumlahMie = etMie.text.toString().toIntOrNull() ?: 0
            val subtotalMie = jumlahMie * HARGA_MIE

            val jumlahRoti = etRoti.text.toString().toIntOrNull() ?: 0
            val subtotalRoti = jumlahRoti * HARGA_ROTI

            val jumlahKopi = etKopi.text.toString().toIntOrNull() ?: 0
            val subtotalKopi = jumlahKopi * HARGA_KOPI

            // 3. Hitung Total Akhir
            val totalHarga = subtotalMie + subtotalRoti + subtotalKopi

            // 4. FORMAT DETAIL PESANAN
            // Logika: Jika jumlah > 0, kirim detail jumlah dan subtotal. Jika = 0, kirim tanda "-"
            val pesananMie = if (jumlahMie > 0) {
                "$jumlahMie porsi (Subtotal: ${formatRupiah(subtotalMie)})"
            } else {
                "-"
            }

            val pesananRoti = if (jumlahRoti > 0) {
                "$jumlahRoti porsi (Subtotal: ${formatRupiah(subtotalRoti)})"
            } else {
                "-"
            }

            val pesananKopi = if (jumlahKopi > 0) {
                "$jumlahKopi porsi (Subtotal: ${formatRupiah(subtotalKopi)})"
            } else {
                "-"
            }

            // 5. Kirim data ke aktivitas Nota
            val keNota = Intent(this, Nota::class.java)

            keNota.putExtra("nama_pemesan", namaPemesan)
            keNota.putExtra("mie_detail", pesananMie)
            keNota.putExtra("roti_detail", pesananRoti)
            keNota.putExtra("kopi_detail", pesananKopi)
            keNota.putExtra("total_harga", totalHarga) // Mengirim Int

            startActivity(keNota)
        }
    }

    private fun init(){
        // Langkah 3: Mengisikan variabel (sesuai ID di XML rumahmakan.xml)
        etNamaPemesan = findViewById(R.id.etNamaPemesan)
        etMie = findViewById(R.id.etMie)
        etRoti = findViewById(R.id.etRoti)
        etKopi = findViewById(R.id.etKopi)
        btPesan = findViewById(R.id.btPesan)
    }
}