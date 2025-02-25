package model;

public class Funcionario implements IPagavel{

    private final double salario;
    private String nome;

    public Funcionario(String nome, double salario){
        this.nome = nome;
        this.salario = salario;
    }

    public String calcularPagamento(){
        return "nome: " + nome + " salario: " + salario;
    }

}
