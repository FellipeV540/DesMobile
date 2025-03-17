package model;

public final class Estudante {
    /**
     *
     */
    private final String nome;
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
    private static final int MEDIA = 7;
    /**
     *
     */
    private static final String APROVADO = "Aprovado";
    /**
     *
     */
    private static final String REPROVADO = "Reprovado";

    /**
     *
     * @return nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     *
     * @param name
     * @param nota1
     * @param nota2
     * @param notaS
     */
    public Estudante(final String name, final int nota1,
                     final int nota2, final int notaS) {
        this.nome = name;
        this.ap1 = nota1;
        this.ap2 = nota2;
        this.as = notaS;
    }

    private boolean calcularMedia(final int n1, final  int n2) {
        return (n1 + n2) / 2 >= MEDIA;
    }

    /**
     *
     * @return se foi aprovado ou não.
     */
    public String aprovado() {
        if (calcularMedia(ap1, ap2)) {
            return APROVADO;
        }


        if (ap1 < ap2) {
            if (as > ap1) {
                ap1 = as;
            }
        } else {
            if (as > ap2) {
                ap2 = as;
            }
        }

        return calcularMedia(ap1, ap2) ? APROVADO : REPROVADO;
    }
}
