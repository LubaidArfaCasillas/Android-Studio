package com.iyas.aplikasipertama // Ganti dengan nama package Anda yang benar

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Pastikan nama kelas ini sesuai dengan yang Anda gunakan di AndroidManifest.xml
class kalkulator : AppCompatActivity() {

    // Variabel untuk menyimpan komponen UI
    private lateinit var tvDisplay: TextView
    private var currentNumber: String = "" // Angka yang sedang dimasukkan pengguna
    private var firstNumber: Double = 0.0  // Angka pertama dalam operasi
    private var currentOperator: String? = null // Operator yang dipilih (+, -, x, ÷)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pastikan nama layout di bawah ini sesuai dengan file XML Anda
        setContentView(R.layout.kalkulator)

        // Inisialisasi TextView untuk tampilan
        tvDisplay = findViewById(R.id.tvDisplay)

        // Atur nilai default display
        tvDisplay.text = "0"

        // Inisialisasi dan Set Listener untuk semua tombol
        setupNumberButtons()
        setupOperatorButtons()
        setupActionButtons()
    }

    // Fungsi untuk menginisialisasi dan mengatur listener tombol angka (0-9)
    private fun setupNumberButtons() {
        val numberButtons = listOf<Button>(
            findViewById(R.id.bt0), findViewById(R.id.bt1), findViewById(R.id.bt2),
            findViewById(R.id.bt3), findViewById(R.id.bt4), findViewById(R.id.bt5),
            findViewById(R.id.bt6), findViewById(R.id.bt7), findViewById(R.id.bt8),
            findViewById(R.id.bt9)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }
    }

    // Fungsi untuk menginisialisasi dan mengatur listener tombol operator (+, -, x, ÷)
    private fun setupOperatorButtons() {
        // Tombol Operator
        findViewById<Button>(R.id.btPlus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btMin).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btKali).setOnClickListener { setOperator("x") }
        findViewById<Button>(R.id.btBagi).setOnClickListener { setOperator("÷") }
    }

    // Fungsi untuk menginisialisasi dan mengatur listener tombol AC, Del, Koma, dan Sama Dengan
    private fun setupActionButtons() {
        // Tombol Aksi
        findViewById<Button>(R.id.btAC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btDelete).setOnClickListener { deleteLast() }
        findViewById<Button>(R.id.btSamaDengan).setOnClickListener { calculateResult() }

        // Tombol Koma (Dot)
        findViewById<Button>(R.id.btKoma).setOnClickListener {
            if (!currentNumber.contains(".")) {
                appendNumber(".")
            }
        }

        // Tombol Persen (saat ini tidak memiliki ID btPersen di XML, menggunakan yang terdekat)
        findViewById<Button>(R.id.btPersen).setOnClickListener {
            // Logika Persen: membagi angka saat ini dengan 100
            if (currentNumber.isNotEmpty() && currentNumber != ".") {
                val value = currentNumber.toDouble() / 100.0
                currentNumber = formatResult(value)
                tvDisplay.text = currentNumber
            }
        }
    }

    // Fungsi untuk menambahkan digit ke currentNumber
    private fun appendNumber(digit: String) {
        if (currentNumber == "0" && digit != ".") {
            // Hapus nol awal jika pengguna memasukkan angka lain
            currentNumber = digit
        } else if (currentNumber.length < 15) { // Batasi panjang angka
            currentNumber += digit
        }
        tvDisplay.text = currentNumber
    }

    // Fungsi untuk mengatur operator dan menyimpan angka pertama
    private fun setOperator(operator: String) {
        if (currentNumber.isNotEmpty() && currentNumber != ".") {
            // Jika sudah ada angka pertama yang tersimpan, hitung hasilnya dulu
            if (currentOperator != null) {
                calculateResult()
            }
            // Simpan angka saat ini sebagai firstNumber
            firstNumber = currentNumber.toDouble()
            currentOperator = operator
            // Reset currentNumber untuk angka kedua
            currentNumber = ""
            // Tampilkan operator di display (opsional, bisa juga tampilkan firstNumber)
            tvDisplay.text = formatResult(firstNumber) + " " + operator
        } else if (currentOperator != null && firstNumber != 0.0 && currentNumber.isEmpty()){
            // Izinkan mengganti operator jika belum ada angka kedua
            currentOperator = operator
            tvDisplay.text = formatResult(firstNumber) + " " + operator
        }
    }

    // Fungsi untuk menghitung hasil berdasarkan firstNumber, currentOperator, dan currentNumber
    private fun calculateResult() {
        if (currentOperator == null || currentNumber.isEmpty() || currentNumber == ".") {
            // Tidak ada operasi yang perlu dilakukan
            return
        }

        val secondNumber = currentNumber.toDouble()
        var result = 0.0

        when (currentOperator) {
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
            "x" -> result = firstNumber * secondNumber
            "÷" -> {
                if (secondNumber != 0.0) {
                    result = firstNumber / secondNumber
                } else {
                    // Penanganan Pembagian dengan Nol
                    tvDisplay.text = "Error: Dibagi Nol"
                    clearState() // Atur ulang keadaan kalkulator
                    return
                }
            }
        }

        // Update keadaan kalkulator dengan hasil
        currentNumber = formatResult(result)
        firstNumber = result
        currentOperator = null

        // Tampilkan hasil
        tvDisplay.text = currentNumber
    }

    // Fungsi untuk menghapus karakter terakhir
    private fun deleteLast() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            if (currentNumber.isEmpty() || currentNumber == "-") {
                currentNumber = "0"
            }
        }
        tvDisplay.text = currentNumber
    }

    // Fungsi untuk mengatur ulang semua keadaan kalkulator (All Clear)
    private fun clearAll() {
        clearState()
        tvDisplay.text = "0"
    }

    // Fungsi bantuan untuk mereset variabel status
    private fun clearState() {
        currentNumber = "0"
        firstNumber = 0.0
        currentOperator = null
    }

    // Fungsi bantuan untuk memformat Double agar tidak menampilkan .0 jika hasil adalah bilangan bulat
    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            // Batasi hingga 10 tempat desimal untuk keterbacaan
            String.format("%.10f", value).trimEnd('0').trimEnd('.')
        }
    }
}