fun main() {
    val lion = Lion("Simba", "África")
    lion.sayHello()

    val asiaticLion = AsiaticLion("Muffasa")
    lion.sayHello()

    val asiaticLion2 = AsiaticLion("Scar")
    lion.sayHello()
}

open class Lion(val name:String, val origin:String) {
    fun sayHello() {
        println("$name, o leão de(a) $origin diz: roaaaar!")
    }
}

open class Tiger

// Kotlin não suporta herança múltipla enre classes
// class AsiaticLion(asiaticName:String):Lion(name = asiaticName, origin = "Ásia"), Tiger()
class AsiaticLion(asiaticName:String):Lion(name = asiaticName, origin = "Ásia")
