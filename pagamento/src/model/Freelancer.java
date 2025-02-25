package model;

public class Freelancer implements IPagavel {

    private double dinPorHora;
    private double horasTrabalhadas;
    private String nome;
    private double dinfinal = 0;

    public Freelancer(String nome, double dinPorHora, double horasTrabalhadas){
        this.nome = nome;
        this.dinPorHora = dinPorHora;
        this.horasTrabalhadas = horasTrabalhadas;
        dinfinal = dinPorHora * horasTrabalhadas;
    }


    public String calcularPagamento(){
        return "nome: " + nome + " Quanto Recebeu: " + dinfinal;
    }

};
