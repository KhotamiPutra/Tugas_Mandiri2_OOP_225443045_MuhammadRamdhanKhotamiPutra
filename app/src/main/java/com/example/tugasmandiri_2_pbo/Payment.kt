package com.example.tugasmandiri_2_pbo

class Payment(
    val order: Order
) {
    private val _amount: Double = order.getTotalFare()

    var method: PaymentMethod = Cash()


    var isPaid: Boolean = false
        private set

  
    fun getAmount(): Double = _amount

    fun processPayment(paidAmount: Double): PaymentResult {
        if (isPaid) {
            return PaymentResult.Failed("Order ini sudah dibayar sebelumnya", 409)
        }
        if (paidAmount < _amount) {
            return PaymentResult.Failed(
                "Nominal pembayaran kurang. Tagihan: Rp$_amount, Dibayar: Rp$paidAmount",
                400
            )
        }
        val result = method.processPayment(_amount)
        if (result is PaymentResult.Success) {
            isPaid = true
        }
        return result
    }


    fun displayPayment() {
        println("=== Detail Pembayaran ===")
        println("Order ID : ${order.id}")
        println("Metode   : ${method.name}")
        println("Nominal  : Rp${"%,.2f".format(_amount)}")
        println("Status   : ${if (isPaid) "Lunas" else "Belum Lunas"}")
    }
}