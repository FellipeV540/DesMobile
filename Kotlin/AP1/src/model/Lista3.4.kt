package model

fun main() {
    val veiculo1 = Veiculo()
    veiculo1.acelerar()

    val carro = Carro()
    carro.acelerar()
    carro.frear()

    val moto1 = Moto()
    moto1.acelerar()
    moto1.frear()
}

open class Veiculo {
    open val tipo:String = "Veiculo genérico"

    open fun acelerar() {
        println("$tipo está acelerando")
    }
    open fun frear() {
        println("$tipo está freando")
    }
}

class Carro : Veiculo() {
    override val tipo:String = "Carro"

    override fun acelerar() {
        println("Acelerando o $tipo")
    }
}

class Moto : Veiculo() {
    override val tipo:String = "Moto"

    override fun acelerar() {
        println("Acelerando a $tipo")
    }
}