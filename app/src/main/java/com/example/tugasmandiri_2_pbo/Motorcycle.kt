package com.example.tugasmandiri_2_pbo


class Motorcycle(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val engineCapacity: Int,
    val hasHelmet: Boolean
) : Vehicle(plateNumber, brand, model, year) {

    override fun getType(): String = "Motor"


    override fun calculateFare(distanceKm: Double): Double {
        return 3000.0 + (distanceKm * 1500.0)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Mesin : ${engineCapacity}cc")
        println("Sedia Helm      : ${if (hasHelmet) "Ya" else "Tidak"}")
    }
}