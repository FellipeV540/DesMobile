package model;

public class Produto {
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, double preco, int quantidadeEstoque){
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String adicionarEstoque(int quantidade){
        if(quantidade < 0){
            return "quantidade invalida";
        }
        else{
            quantidadeEstoque += quantidade;
            return quantidade + " unidades adicionadas ao estoque";
        }
    }

    public String vender(int quantidade){
        if(quantidadeEstoque - quantidade < 0){
            return "quantidade invalida";
        }
        else{
            quantidadeEstoque -= quantidade;
            return quantidade + " unidades vendidas";
        }
    }
    public double getPreco(){
        return preco;
    }
    public String getNome(){
        return nome;
    }

    public double getEstoque(){
        return quantidadeEstoque;
    }
}
