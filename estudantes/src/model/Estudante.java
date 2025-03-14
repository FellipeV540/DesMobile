package model;

public class Estudante {
    /**
     *
     */
    private String nome;
    /**
     *
     */
    private int ap1;
    /**
     *
     */
    private int ap2;
    /**
     *
     */
    private int as;
    /**
     *
     */
    private final int media = 7;

    /**
     *
     * @param name
     * @param nota1
     * @param nota2
     * @param notaS constructor
     */
    public Estudante(final String name, final int nota1,
                     final int nota2, final int notaS) {
        this.ap1 = nota1;
        this.ap2 = nota2;
        this.as = notaS;
        this.nome = name;

    }

    /**
     *
     * @return se o aluno é aprovado ou nao
     */
    public String aprovado() {
        if ((ap1 + ap2) / 2 >= media) {
            return "Aprovado";
        } else if (ap1 < ap2 && as > ap1) {
            ap1 = as;
            if ((ap1 + ap2) / 2 >= media) {
                return "Aprovado";
            } else {
                return "Reprovado";
            }
        } else if (ap2 < ap1 && as > ap2) {
            ap2 = as;
            if ((ap1 + ap2) / 2 >= media) {
                return "Aprovado";
            } else {
                return "Reprovado";
            }
        } else if (ap1 == ap2 &&  ap1 < as) {
            ap1 = as;
            if ((ap1 + ap2) / 2 >= media) {
                return "Aprovado";
            } else {
                return "Reprovado";
            }
        } else {
            return "Reprovado";
        }
    };

}
