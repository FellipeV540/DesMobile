package control;

import model.Produto;

public class Programa {
    public static void main(String[] args) {
        Produto produto1 = new Produto("rainretr", 400, 100);

        System.out.println(produto1.getNome());
        System.out.println(produto1.getPreco());
        System.out.println(produto1.getEstoque());
        System.out.println(produto1.adicionarEstoque(-500));
        System.out.println(produto1.adicionarEstoque(500));
        System.out.println(produto1.getEstoque());
        System.out.println(produto1.vender(1001));
        System.out.println(produto1.vender(500));
        System.out.println(produto1.getEstoque());
    }
}
