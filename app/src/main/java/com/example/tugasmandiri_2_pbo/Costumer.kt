package com.example.tugasmandiri_2_pbo

class Customer(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
){

    var balance : Double = 0.0
        private set

    fun displayInfo(){
        println("=== Info Customer ===")
        println("ID     : $id")
        println("Nama   : $name")
        println("Telepon: $phone")
        println("Email  : $email")
        println("Saldo  : Rp${"%,.2f".format(balance)}")
    }

    fun topUp(amount: Double){
        balance += amount
    }

    fun canPay(amount: Double):Boolean{
        return balance >= amount
    }
}