package model;

public class Aluno extends Pessoa implements IPessoa{

    private String curso;

    public Aluno(String nome, String matricula, String curso) {
        super(nome, matricula);
        this.curso = curso;
    }

    public String saudacao(){
        return "Olá, sou aluno do curso " + curso;
    }
}
