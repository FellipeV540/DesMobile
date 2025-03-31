fun main() {
    val veiculo1 = Veiculo()
    veiculo1.acelerar()

    val carro = Carro()
    carro.acelerar()
}

open class Veiculo {
    open val tipo:String = "Veiculo genérico"

    open fun acelerar() {
        println("$tipo está acelerando")
    }
}

class Carro : Veiculo() {
    override val tipo:String = "Carro"

    override fun acelerar() {
        println("$tipo está acelerando rapidamente")
    }
}