package model;

public class Gato extends Animal{
    public Gato(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
        }

    public String dados(){
        return "nome: " + nome + " idade: " + idade;
    }
    public String emitirSom() {
        return "Meow";
    }
}

