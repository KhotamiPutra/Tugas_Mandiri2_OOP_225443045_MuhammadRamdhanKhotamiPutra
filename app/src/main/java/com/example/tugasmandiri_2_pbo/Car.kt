package com.example.tugasmandiri_2_pbo


class Car(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val fuelType: String,
    val numberOfDoors: Int
) : Vehicle(plateNumber, brand, model, year) {

    override fun getType(): String = "Mobil"

    override fun calculateFare(distanceKm: Double): Double {
        return 8000.0 + (distanceKm * 2500.0)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Bahan Bakar : $fuelType")
        println("Jml Pintu   : $numberOfDoors")
    }
}