package com.example.tugasmandiri_2_pbo

class Order(
    val id: String,
    val customer: Customer,
    val driver: Driver,
    val pickupLocation: String,
    val destination: String,
    val distanceKm: Double,
) {
    var status: OrderStatus = OrderStatus.Waiting
        private set

    fun getTotalFare(): Double = driver.vehicle.calculateFare(distanceKm)

    fun startTrip(): Boolean {
        if (status != OrderStatus.Waiting || !driver.acceptOrder()) return false
        driver.vehicle.isAvailable = false
        status = OrderStatus.OnGoing
        return true
    }

    fun completeTrip(): Boolean {
        if (status != OrderStatus.OnGoing) return false
        driver.vehicle.isAvailable = true
        status = OrderStatus.Completed
        return true
    }

    fun cancelTrip(reason: String): Boolean {
        if (status.isFinal()) return false
        driver.vehicle.isAvailable = true
        status = OrderStatus.Cancelled(reason)
        return true
    }

    fun displayOrder() {
        println("=== Detail Order ===")
        println("ID Order  : $id")
        println("Customer   : ${customer.name}")
        println("Driver     : ${driver.name}")
        println("Rute       : $pickupLocation -> $destination")
        println("Jarak      : ${distanceKm} km")
        println("Tarif      : Rp${"%,.2f".format(getTotalFare())}")
        println("Status     : ${status.display()}")
    }
}