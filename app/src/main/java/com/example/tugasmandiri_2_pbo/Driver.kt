package com.example.tugasmandiri_2_pbo


class Driver(
    val id: String,
    val name: String,
    val phone: String,
    val vehicle: Vehicle
) {

    var isActive: Boolean = true


    fun displayInfo() {
        println("=== Info Driver ===")
        println("ID     : $id")
        println("Nama   : $name")
        println("Telepon: $phone")
        println("Aktif  : ${if (isActive) "Ya" else "Tidak"}")
        vehicle.displayInfo()
    }


    fun acceptOrder(): Boolean {
        return isActive && vehicle.isAvailable
    }
}