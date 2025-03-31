fun main() {
    // Listas em Kotlin podem ser declaradas como mutáveis ou imutáveis
    // Listas imutáveis são declaradas com List
    // Listas mutáveis são declaradas com MutableList

    val listaNumeros1:List<Int>
    val listaNumeros2:List<Int> = listOf(1, 2, 3, 4, 5)

    // listaNumero2.add(6) // Unresolved reference 'add'.

    println("Tamanho da lista: " + listaNumeros2.size)

    // percorrendo os elentos de uma lista
    for(numero in listaNumeros2) {
        println(numero)
    }

    val times:MutableList<String> = mutableListOf("Vasco", "Botafogo", "Fluminense", "América")
    times.add("Bangu")

    for(time in times) {
        println(time)
    }

    // A mutabilidade de uma lista está relacionada tanto ao tipo de lista (List ou MutableList)
    //quanto a maneira como a variável que contém a lista é declarada (var ou val)
    // list representa uma oleção ordenada de itens, qe não pode ser modificada após sua criação.
    // MutableList permite a mdificação após sua criação. Possui métodos como add() e remove ().

    val listaImutavelA:List<String> = listOf("a", "b", "c")
    // listaImutavelA.add("d") // Unresolved reference 'add'.
    // listaImutavelA = listOf("x", "y", "z") // 'val' cannot be reassigned

    var listaImutavelB:List<String> = listOf("a", "b", "c")
    // listaImutavelB.add("d") // Unresolved reference 'add'.
    listaImutavelB = listOf("x", "y", "z")

    val listaMutavelA:MutableList<String> = mutableListOf("a", "b", "c")
    listaMutavelA.add("d")
    // listaMutavelA = mutableListOf("x", "y", "z") // 'val' cannot be reassigned


    var listaMutavelB:MutableList<String> = mutableListOf("a", "b", "c")
    listaMutavelB.add("d")
    listaMutavelB = mutableListOf("x", "y", "z")

}