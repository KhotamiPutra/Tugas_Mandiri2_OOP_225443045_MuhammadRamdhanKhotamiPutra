package com.example.tugasmandiri_2_pbo

import java.time.Year

open class Vehicle(
    val plateNumber: String,
    val brand: String,
    val model: String,
    val year: Int
) {

    var isAvailable: Boolean = true


    open fun getType(): String = "Kendaraan Umum"


    open fun calculateFare(distanceKm: Double): Double {
        return 5000.0 + (distanceKm * 2000.0)
    }

    open fun displayInfo() {
        println("=== Info Kendaraan ===")
        println("Jenis       : ${getType()}")
        println("Plat Nomor  : $plateNumber")
        println("Merek/Model : $brand $model")
        println("Tahun       : $year")
        println("Tersedia    : ${if (isAvailable) "Ya" else "Tidak"}")
    }
}