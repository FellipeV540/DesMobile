package control;

import model.Moto;
import model.Carro;
import model.Veiculo;

public class Programa {

    public static void main(String[] args) {
        //Carro carro1 = new Carro(4, 4);
        Moto moto1 = new Moto(2);

        System.out.println(moto1.acelerar());
        System.out.println(moto1.frear());

       // System.out.println(carro1.acelerar());
       // System.out.println(carro1.frear());
    }
}
