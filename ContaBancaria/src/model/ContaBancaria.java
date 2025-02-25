package model;

public class ContaBancaria {
    private String numeroConta;
    private String titular;
    private double saldo = 0;

    public ContaBancaria(String titular, String numeroConta){
        this.titular = titular;
        this.numeroConta = numeroConta;
    }

    public String depositar(double valor){
        if(valor <= 0){
            return "Deposito Invalido";
        }
        else{
            saldo = saldo + valor;
            return "Deposito de: " + valor;
        }
    }
    public String sacar(double valor){
        if (saldo - valor < 0){
            return "Saque Invalido";

        }
        else {
            saldo = saldo - valor;
            return "Saque de: " + valor;
        }
    }
    public String getSaldo(){
        return "saldo: " + saldo;
    }
    public String getNome(){
        return "titlar: " + titular;
    }
}
