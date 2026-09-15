package com.example.tugasmandiri_2_pbo

interface PaymentMethod {
    val name: String

    fun processPayment(amount: Double): PaymentResult

    fun getFee(amount: Double): Double = 0.0
}

class CreditCard(private val cardNumber: String) : PaymentMethod {

    override val name: String = "Kartu Kredit"

    init {
        require(cardNumber.length >= 16) { "Nomor kartu kredit minimal 16 digit" }
    }

    override fun getFee(amount: Double): Double = amount * 0.02

    override fun processPayment(amount: Double): PaymentResult {
        val fee = getFee(amount)
        val transactionId = "CC-${System.currentTimeMillis()}"
        val timestamp = java.time.LocalDateTime.now().toString()
        println("Memproses Kartu Kredit: total dibayar Rp${amount + fee} (termasuk fee Rp$fee)")
        return PaymentResult.Success(transactionId, timestamp)
    }
}

class QRIS(private val qrCode: String) : PaymentMethod {

    override val name: String = "QRIS"

    init {
        require(qrCode.length >= 10) { "Kode QR minimal 10 karakter" }
    }

    override fun getFee(amount: Double): Double = amount * 0.005

    override fun processPayment(amount: Double): PaymentResult {
        val fee = getFee(amount)
        val transactionId = "QR-${System.currentTimeMillis()}"
        val timestamp = java.time.LocalDateTime.now().toString()
        println("Memproses QRIS: total dibayar Rp${amount + fee} (termasuk fee Rp$fee)")
        return PaymentResult.Success(transactionId, timestamp)
    }
}

class Cash : PaymentMethod {

    override val name: String = "Tunai"

    override fun getFee(amount: Double): Double = 0.0

    override fun processPayment(amount: Double): PaymentResult {
        val transactionId = "CASH-${System.currentTimeMillis()}"
        val timestamp = java.time.LocalDateTime.now().toString()
        println("Memproses Tunai: total dibayar Rp$amount")
        return PaymentResult.Success(transactionId, timestamp)
    }
}