package control;

import model.Freelancer;
import model.Funcionario;

public class Programa {
    public static void main(String[] args) {
        Funcionario funcionario1 = new Funcionario("Alex", 15000);
        Freelancer freela1 = new Freelancer("Lexa", 60, 8);

        System.out.println(funcionario1.calcularPagamento());
        System.out.println(freela1.calcularPagamento());
    }
}
