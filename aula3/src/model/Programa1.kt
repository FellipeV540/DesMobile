fun main() {
    var pessoaA = PessoaA() // var permite a reatribuição do objeto
    val pessoaA2 = PessoaA() // val não permite a reatribuição do objeto
    pessoaA = pessoaA2
    // pessoaA2 = pessoaA // Erro porque val não pode ser reatribuido

    val pessoaB = PessoaB()
    val pessoaC = PessoaC()
    pessoaC.saudacao()
    val pessoaD = PessoaD()
    pessoaD.nome = "Jõao"
    // pessoaD.dataNascimento = "2001-01-01" // Erro porque val ão pode er reatribuido
    val pessoaE = PessoaE("José", "2000-01-01") // Passagem de parâmetros para o metodo construtor
    val pessoaF = PessoaF("José", "2000-01-01")
    val pessoaG = PessoaG("José", "2000-01-01")
    pessoaG.endereco = "R. Roberto Dinaminte"
    pessoaG.vasco()
}

// Uma classe sem atributos ou funções pode ser declarada sem chaves
class PessoaA

// Uma classe sem atributos ou funções, porém com chaves
class PessoaB {

}

// Uma classe sem atributos, porém com uma função
class PessoaC {
    fun saudacao() {
        println("Olá!")
    }
}

// Uma classe com atributos, mas sem funções
class PessoaD {
    var nome:String = "José"
    val dataNascimento:String = "2000-01-01"
}

// Uma lasse sem atributos ou funções, sem chaves, com metodo construtor passando parametros
class PessoaE(var nome:String, val dataNascimento:String)

// Uma lasse sem atributos ou funções, sem chaves, com metodo construtor passando parametros
class PessoaF(var nome:String, val dataNascimento:String) {

}

// Uma classe com atributo, com função, com chaves e com metodo construtor passando parâmetros
class PessoaG(var nome:String, val dataNascimento:String) {
    var endereco:String = "Avenida dali da silva, 233"
    fun vasco() {
        println("Vasco!")
    }
}