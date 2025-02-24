package model;

public class Carro extends Veiculo implements IVeiculo, Interface1, Interface2{

    public String acelerar(){
        return "Acelerando o Carro";
    }

    public String frear(){
        return "Freando o Carro";
    }
}
