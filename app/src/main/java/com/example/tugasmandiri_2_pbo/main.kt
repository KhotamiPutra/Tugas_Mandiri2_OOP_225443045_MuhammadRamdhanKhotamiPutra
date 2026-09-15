package com.example.tugasmandiri_2_pbo

fun main() {
    val system = TransportSystem("Go-Transport 2024")

    val car = Car("B 1234 XYZ", "Toyota", "Innova", 2021, "Bensin", 4)
    val motorcycle = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, 125, true)
    val truck = Truck("E 9012 DEF", "Hino", "Dutro", 2020, 5.0, 2)
    system.addVehicle(car)
    system.addVehicle(motorcycle)
    system.addVehicle(truck)

    val driver1 = Driver("D001", "Andi", "08123456789", car)
    val driver2 = Driver("D002", "Budi", "08129876543", motorcycle)
    val driver3 = Driver("D003", "Citra", "08125678901", truck)
    system.addDriver(driver1)
    system.addDriver(driver2)
    system.addDriver(driver3)

    val customer1 = Customer("C001", "Dewi", "08134567890", "dewi@email.com").apply { topUp(100000.0) }
    val customer2 = Customer("C002", "Eko", "08135678901", "eko@email.com").apply { topUp(50000.0) }
    val customer3 = Customer("C003", "Fani", "08136789012", "fani@email.com").apply { topUp(200000.0) }
    system.addCustomer(customer1)
    system.addCustomer(customer2)
    system.addCustomer(customer3)

    system.displayAllVehicles()
    system.displayAllDrivers()
    system.displayAllCustomers()

    val order1 = system.createOrder("C001", "D001", "Kampus A", "Mall B", 12.0)
    val order2 = system.createOrder("C002", "D002", "Stasiun", "Kantor", 8.0)
    val order3 = system.createOrder("C003", "D003", "Gudang", "Pelabuhan", 25.0)

    system.displayAllOrders()

    order1?.startTrip()
    order2?.startTrip()

    if (order1 != null) {
        val qrisResult = system.processPayment(order1.id, QRIS("QRCODE12345"), order1.getTotalFare())
        println("\nHasil Pembayaran Order 1 (QRIS): ${qrisResult.display()}")
    }

    if (order2 != null) {
        val kurang = order2.getTotalFare() - 10000.0
        val gagalResult = system.processPayment(order2.id, Cash(), kurang)
        println("\nHasil Pembayaran Order 2 percobaan 1 (Tunai, kurang): ${gagalResult.display()}")

        customer2.topUp(50000.0)
        val ccResult = system.processPayment(order2.id, CreditCard("1234567890123456"), order2.getTotalFare())
        println("Hasil Pembayaran Order 2 percobaan 2 (Kartu Kredit): ${ccResult.display()}")
    }

    order1?.let { system.completeOrder(it.id) }

    order3?.let { system.cancelOrder(it.id, "Hujan deras") }

    system.displayAllOrders()

    system.displayRevenueReport()

    println("\n--- Demonstrasi Polimorfisme ---")
    for (v in system.getVehicles()) {
        println("${v.getType()} (${v.plateNumber}) -> Tarif 15km: Rp${v.calculateFare(15.0)}")
    }

    println("\n--- Demonstrasi Smart Casting ---")
    val vehicleOfDriver1: Vehicle = driver1.vehicle
    if (vehicleOfDriver1 is Car) {
        println("Kendaraan Driver D001 adalah Mobil dengan bahan bakar: ${vehicleOfDriver1.fuelType}")
    }
    val maybeTruck = vehicleOfDriver1 as? Truck
    println("Hasil casting aman (as? Truck) dari kendaraan Driver D001: $maybeTruck")

    println("\n--- Demonstrasi Sealed Class OrderStatus ---")
    val statuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.OnGoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Contoh alasan pembatalan")
    )
    for (status in statuses) {
        val text = when (status) {
            is OrderStatus.Waiting -> status.display()
            is OrderStatus.OnGoing -> status.display()
            is OrderStatus.Completed -> status.display()
            is OrderStatus.Cancelled -> status.display()
        }
        println("Status: $text | Final?: ${status.isFinal()}")
    }
}