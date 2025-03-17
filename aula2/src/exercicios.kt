//exec3
const val PI:Double = 3.14

fun main() {
    //exec1
    println("exec1")
    val nome = "Maria"
    var idade = 30

    // nome = "Jõao" // num da
    idade = 31

    //exec2
    println()
    println("exec2")
    var altura:Double = 1.75
    println(altura)
    var alturaInteira:Int = altura.toInt()
    println(alturaInteira)

    //exec3
    println()
    println("exec3")
    val raio = readln().toDouble()
    println("Raio do Circulo: " + PI * raio * raio)

    //exec4
    println()
    println("exec4")
    val numeros:List <Int> = listOf(1, 2, 3, 4, 5)
    //numeros.add(6)
    //numeros[4] = 6

    //exec5
    println()
    println("exec5")
    println(numeros[0])
    println(numeros[4])
    println(numeros.size)

    //exec6
    println()
    println("exec6")
    var numerosPar:MutableList <Int> = mutableListOf()
    for (num in numeros){
        if (num % 2 == 0) {
            numerosPar.add(num)
        }
    }
    println(numerosPar)

    //exec7
    println()
    println("exec7")
    var compras:MutableList <String> = mutableListOf("Maça", "Banana", "Uva")
    compras.add("Morango")
    compras.remove("Banana")
    compras.replaceAll()
    println(compras)
}
