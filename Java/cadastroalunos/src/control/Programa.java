package control;

import model.Aluno;

public class Programa {

    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("123", "Maria", "CDIA");
//        aluno1.setMatricula("123");
//        aluno1.setNome("Maria");
//        aluno1.setCurso("CDIA");
        Aluno aluno2 = new Aluno("124", "João", "ADS");

        System.out.println("Nome: " + aluno1.getNome());
        System.out.println("Matricula: " + aluno1.getMatricula());
        System.out.println("Curso: " + aluno1.getCurso());

        System.out.println(" ");

        System.out.println("Nome: " + aluno2.getNome());
        System.out.println("Matricula: " + aluno2.getMatricula());
        System.out.println("Curso: " + aluno2.getCurso());
    }
}
