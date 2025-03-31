package model

fun main(){
    var lista:MutableList<Int> = mutableListOf(1, 2, 3, 7, 7, 6, 7)
    var maiorN:Int = 0

    fun maior() {
        for (i in lista) {
            if(i > maiorN){
                maiorN = i
            }
            else {
                continue
            }
        }
    }
    maior()
    println(maiorN)
}
