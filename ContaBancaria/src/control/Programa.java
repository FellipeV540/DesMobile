package control;

import model.ContaBancaria;

public class Programa {
    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria("Alex", "123");
        System.out.println(conta1.depositar(500));
        System.out.println(conta1.getSaldo());
        System.out.println(conta1.depositar(-1500));
        System.out.println(conta1.sacar(501));
        System.out.println(conta1.sacar(250));
        System.out.println(conta1.getSaldo());
        System.out.println(conta1.getNome());

    }
}
