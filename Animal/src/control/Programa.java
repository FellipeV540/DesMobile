package control;

import model.Cachorro;
import model.Gato;

public class Programa {
    public static void main(String[] args) {
        Cachorro cachorro1 = new Cachorro("alex", 4);
        Gato gato1 = new Gato("Asteroid Destroyer", 3);

        System.out.println(cachorro1.emitirSom());
        System.out.println(cachorro1.dados());

        System.out.println(gato1.emitirSom());
        System.out.println(gato1.dados());
    }
}
