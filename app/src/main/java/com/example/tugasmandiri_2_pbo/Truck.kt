package com.example.tugasmandiri_2_pbo


class Truck(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val loadCapacity: Double,
    val numberOfAxles: Int
) : Vehicle(plateNumber, brand, model, year) {


    override fun getType(): String = "Truk"


    override fun calculateFare(distanceKm: Double): Double {
        return 10000.0 + (distanceKm * 3500.0)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Muat : ${loadCapacity} ton")
        println("Jml Sumbu      : $numberOfAxles")
    }
}