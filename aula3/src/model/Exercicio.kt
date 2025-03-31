fun main() {
    val barbie = Barbie("Barbie", "loiro")
    val ken = Ken("Ken", "castanho")
    val kenFalso = Ken("Ken Falso", "ruivo")

    barbie.falar()
    ken.falar()
    kenFalso.falar()
}

open class Boneco(var nome:String, var corDeCabelo:String) {
    open var vestimenta:String = "roupa básica"

    open fun falar() {
        println("Olá, sou um(a) boneco(a). Meu nome é $nome.")
    }
}

class Barbie(nome:String, corDeCabelo:String): Boneco(nome = nome, corDeCabelo = corDeCabelo) {
    override var vestimenta = "vestido rosa"

    override fun falar() {
        println("Olá, sou a Barbie. A cor do meu cabelo é $corDeCabelo.")
    }
}

class Ken(nome:String, corDeCabelo:String): Boneco(nome = nome, corDeCabelo = corDeCabelo){
    override fun falar() {
        println("Hi, Barbie. Eu sou o $nome. A cor do meu cabelo é $corDeCabelo.")
    }
}