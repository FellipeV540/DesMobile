package model
fun main(){
        var listaBrasileiros:MutableList<String> = mutableListOf()

    fun medalNatacao(name:String) {
        listaBrasileiros.add(name)
    }
    medalNatacao("José da Silva")
    println(listaBrasileiros)

}
