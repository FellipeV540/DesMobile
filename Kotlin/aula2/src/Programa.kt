fun main(){
        println(saudacao1(false))
        println(saudacao2(true))
        println(saudacao3(true))
    }

    fun saudacao1(dia:Boolean):String {
        if(dia) {
            return "Bom Dia!"
        } else {
            return "Boa Noite!"
        }
    }

    fun saudacao2(dia:Boolean):String {
        return if(dia) {
            "Bom Dia!"
        } else {
            "Boa Noite!"
        }
    }

    // Versão com tipo de retorno inferido e sem return
    fun saudacao3(dia:Boolean) = if(dia) {
        "Bom Dia!"
    } else {
        "Boa Noite!"
    }
