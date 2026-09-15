package com.example.tugasmandiri_2_pbo

sealed class PaymentResult {

    abstract fun display(): String

    data class Success(val transactionId: String, val timestamp: String) : PaymentResult() {
        override fun display(): String =
            "Pembayaran BERHASIL | ID Transaksi: $transactionId | Waktu: $timestamp"
    }


    data class Failed(val reason: String, val errorCode: Int) : PaymentResult() {
        override fun display(): String =
            "Pembayaran GAGAL | Alasan: $reason | Kode Error: $errorCode"
    }

    object Pending : PaymentResult() {
        override fun display(): String = "Pembayaran PENDING"
    }
}