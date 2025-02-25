package model;

public class Funcionario {
    private String nome;
    protected double salario;

    public Funcionario(String nome, double salario){
        this.nome = nome;
        this.salario = salario;
    }

    public String getSalario(){
        return "Salario: " + salario;
    }
    public String getNome(){
        return "Nome: " + nome;
    }

    public String aumentarSalario(double percentual){
        double aumento = percentual / 100;
        double bonus = salario * aumento;
        salario += bonus;
        return "Aumento de " + percentual + "% no salario";

    }
}
