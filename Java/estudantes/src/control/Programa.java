package control;

import model.Estudante;

public class Programa {
    public static void main(String[] args) {
        Estudante estudante1 = new Estudante("Rai", 5, 6, 8);
        Estudante estudante2 = new Estudante("Fellipe", 7, 7, 0);
        Estudante estudante3 = new Estudante("Andre", 7, 2, 3);
        Estudante estudante4 = new Estudante("Abdu", 5, 5, 9);

        System.out.println(estudante1.getNome());
        System.out.println(estudante1.aprovado());
        System.out.println(estudante2.getNome());
        System.out.println(estudante2.aprovado());
        System.out.println(estudante3.getNome());
        System.out.println(estudante3.aprovado());
        System.out.println(estudante4.getNome());
        System.out.println(estudante4.aprovado());
    }
}
