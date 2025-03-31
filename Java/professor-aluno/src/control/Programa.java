package control;

import model.Aluno;
import model.Monitor;
import model.Professor;

public class Programa {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("Rai", "1234", "Ads");

        Professor professor1 = new Professor("Thiago", "1235", 80);

        Monitor monitor1 = new Monitor("Abdu", "1236", "CDIA", "PMobile");

        System.out.println(aluno1.saudacao());
        System.out.println(monitor1.saudacao());
    }
}
