package com.example.tugasmandiri_2_pbo

class TransportSystem(val name: String) {

    private val vehicles: MutableList<Vehicle> = mutableListOf()
    private val drivers: MutableList<Driver> = mutableListOf()
    private val customers: MutableList<Customer> = mutableListOf()
    private val orders: MutableList<Order> = mutableListOf()
    private val payments: MutableList<Payment> = mutableListOf()

    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    fun addDriver(driver: Driver) {
        drivers.add(driver)
    }

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    fun findVehicle(plateNumber: String): Vehicle? =
        vehicles.find { it.plateNumber == plateNumber }

    fun findDriver(id: String): Driver? =
        drivers.find { it.id == id }

    fun findCustomer(id: String): Customer? =
        customers.find { it.id == id }

    fun getVehicles(): List<Vehicle> = vehicles

    fun createOrder(
        customerId: String,
        driverId: String,
        pickup: String,
        dest: String,
        distance: Double
    ): Order? {
        val customer = findCustomer(customerId) ?: return null
        val driver = findDriver(driverId) ?: return null
        val orderId = "ORD${(orders.size + 1).toString().padStart(3, '0')}"
        val order = Order(orderId, customer, driver, pickup, dest, distance)
        orders.add(order)
        return order
    }

    fun processPayment(orderId: String, method: PaymentMethod, paidAmount: Double): PaymentResult {
        val order = orders.find { it.id == orderId }
            ?: return PaymentResult.Failed("Order dengan ID $orderId tidak ditemukan", 404)

        val payment = payments.find { it.order.id == orderId } ?: Payment(order).also {
            payments.add(it)
        }
        payment.method = method
        return payment.processPayment(paidAmount)
    }

    fun completeOrder(orderId: String): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        return order.completeTrip()
    }

    fun cancelOrder(orderId: String, reason: String): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        return order.cancelTrip(reason)
    }

    fun displayAllVehicles() {
        println("\n--- Daftar Kendaraan ($name) ---")
        vehicles.forEach { it.displayInfo(); println() }
    }

    fun displayAllDrivers() {
        println("\n--- Daftar Driver ($name) ---")
        drivers.forEach { it.displayInfo(); println() }
    }

    fun displayAllCustomers() {
        println("\n--- Daftar Customer ($name) ---")
        customers.forEach { it.displayInfo(); println() }
    }

    fun displayAllOrders() {
        println("\n--- Daftar Order ($name) ---")
        orders.forEach { it.displayOrder(); println() }
    }

    fun displayRevenueReport() {
        val completedOrders = orders.filter { it.status == OrderStatus.Completed }
        val totalRevenue = completedOrders.sumOf { it.getTotalFare() }
        println("\n--- Laporan Pendapatan ($name) ---")
        println("Jumlah Order Selesai : ${completedOrders.size}")
        println("Total Pendapatan     : Rp${"%,.2f".format(totalRevenue)}")
    }
}