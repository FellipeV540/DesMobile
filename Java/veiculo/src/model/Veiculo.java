package model;

abstract public class Veiculo {

    private int numRodas;

    private final int XPTO = 10;

    public Veiculo(int numeroRodas) {
        this.numRodas = numeroRodas;
    }

    abstract public String acelerar();

    abstract public String frear();
}
