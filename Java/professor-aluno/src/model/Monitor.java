package model;

public class Monitor extends Aluno{

    private String disciplina;

    public Monitor(String nome, String matricula, String curso, String disciplina) {
        super(nome, matricula, curso);
        this.disciplina = disciplina;
    }

    public String saudacao(){
        return super.saudacao();
    }
}
