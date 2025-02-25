package model;

public class Gerente extends Funcionario{

    public Gerente(String nome, double salario){
        super(nome, salario);
    }

    public String bonificacao(){
        double bonus = 1500;
        salario = salario + bonus;
        return "Bonus de " + bonus + ", Salario atual: " + salario;
    }
}
