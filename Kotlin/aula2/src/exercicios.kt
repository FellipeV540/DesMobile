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
    compras.set(2, "Pera")
    println(compras)

    //exec8
    println()
    println("exec8")
    var numerosAleatorios:MutableList <Int> = mutableListOf(5, 4, 1, 3, 8, 6)
    var n1 = numerosAleatorios.size
    for (i in 0 until n1 - 1) {
    	for (j in 0 until n1 - i - 1) {
        	if (numerosAleatorios[j] > numerosAleatorios[j+1]) {
            	var temp = numerosAleatorios[j]
            	temp = numerosAleatorios[j]
            	numerosAleatorios[j] = numerosAleatorios[j+1]
            	numerosAleatorios[j+1] = temp
        	}
    	}
	}
    println(numerosAleatorios)

    //exec9
    println()
    println("exec9")
    var alunos:MutableList <String> = mutableListOf("Rai", "Andre", "jpabu")
    alunos.forEach{
        println(it)
    }

    //exec10
    println()
    println("exec10")
    var numRepetido:MutableList <Int> = mutableListOf(2, 2, 2, 2, 3, 6, 3)
    var n2 = numRepetido.size
    var numSemRepetido = numRepetido.toSet()
    println(numSemRepetido)
}
