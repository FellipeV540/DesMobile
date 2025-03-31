package model;

public class Cachorro extends Animal{
    public Cachorro(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String dados(){
        return "nome: " + nome + " idade: " + idade;
    }
    public String emitirSom() {
        return "Au Au";
    }
}
