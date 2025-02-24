package model;

public class Professor extends Pessoa{

    private int cargaHoraria;

    public Professor(String nome, String matricula, int cargaHoraria) {
        super(nome, matricula);
        this.cargaHoraria = cargaHoraria;
    }
}
