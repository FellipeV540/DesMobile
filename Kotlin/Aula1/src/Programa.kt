fun main() {
            println(tipoTriangulo(2,2,2))
            println(tipoTriangulo(2,2,3))
            println(tipoTriangulo(2,3,4))
            println(tipoTriangulo(0,2,2))
            println(tipoTriangulo(1,2,3))
        }

        fun tipoTriangulo(num1:Int, num2:Int, num3:Int):String {
            if((num1 + num2) > num3){
                if (num1 == num2 && num2 == num3){
                    return "equilatero"
                }
                else if (num1 == num2 || num1 == num3 || num2 == num3){
                    return "isosceles"
                }
                else {
                    return "escaleno"
                }
            }
            else {
                return "não é um triangulo"
            }
        }

