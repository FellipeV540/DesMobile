fun main() {
    
}

open class Boneco(nome:String, corDeCabelo) {
    var nome:String
    var corDeCabelo:String
    open var vestimenta:String = "roupa básica"

    open fun falar() {
        println("Olá, sou um(a) boneco(a). Meu nome é $nome.")
    }
}

class Barbie: Boneco {
    override vestimenta = "vestido rosa"

    override fun falar() {
        println("Olá, sou a Barbie. A cor do meu cabelo é $corDeCabelo.")
    }
}

class Ken: Boneco {
    override fun falar() {
        println("Hi, Barbie. Eu sou o $nome. A cor do meu cabelo é $corDeCabelo.")
    }
}