package control;

import model.Funcionario;
import model.Gerente;

public class Programa {
    public static void main(String[] args) {
        Funcionario func1 = new Funcionario("Alex", 3000);
        Gerente gerente1 = new Gerente("Axel", 5000);

        System.out.println(func1.getNome());
        System.out.println(func1.getSalario());
        System.out.println(func1.aumentarSalario(10));
        System.out.println(func1.getSalario());

        System.out.println(gerente1.getNome());
        System.out.println(gerente1.getSalario());
        System.out.println(gerente1.aumentarSalario(10));
        System.out.println(gerente1.getSalario());
        System.out.println(gerente1.bonificacao());
        System.out.println(gerente1.getSalario());
    }
}
