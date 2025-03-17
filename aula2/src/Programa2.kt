const val IDADE_MINIMA = 18 //declaração de constante

fun main() {
    var nome = "Enzo" // Variavel mutavel
    val dataNascimento = "2025-03-17" // Variavl imutável

    println(nome)
    println(dataNascimento)

    nome = "Rai"
    // dataNascimento = "2025-03-18" // 'val' cannot be reassigned.

    println(nome)
    print(dataNascimento)
}