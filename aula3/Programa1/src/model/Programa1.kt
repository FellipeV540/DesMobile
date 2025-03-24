fun main() {
    var pessoaA = PessoaA()
    val pessoaA2 = PessoaA()
    pessoaA = pessoaA2
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