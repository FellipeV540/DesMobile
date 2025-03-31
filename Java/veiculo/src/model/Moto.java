package model;

public class Moto extends Veiculo{

    public Moto(int numeroRodas){
        super(numeroRodas);
    }

    public String acelerar(){
    return "Acelerando a Moto";
    }

    public String frear(){
        return "Freando a Moto";
    }
}
