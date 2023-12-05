import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Exemplar {
    private int codigoLivro;
    private int codigoExemplar;
    private boolean disponivel;
    private Date dataEmprestimo;
    private Livro livro;

    public Exemplar(int codigoLivro, int codigoExemplar) {
        this.codigoLivro = codigoLivro;
        this.codigoExemplar = codigoExemplar;
        this.disponivel = true;
        this.dataEmprestimo = null;;
    }

    static Exemplar encontrarExemplarPorCodigo(List<Exemplar> exemplares, int codigoLivro) {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getCodigoLivro() == codigoLivro && exemplar.isDisponivel()) {
                System.out.println("Exemplar encontrado:" + codigoLivro);
                return exemplar;
            }
        }
        return null;
    }

    public int getCodigoLivro() {
        return codigoLivro;
    }
    public boolean isDisponivel() {

        return disponivel;
    }

    public Date getDataEmprestimo() {

        return dataEmprestimo;
    }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            dataEmprestimo = new Date();
        }
    }

    public void devolver() {
        if (!disponivel) {
            disponivel = true;
            dataEmprestimo = null;
        }
    }

    public void setDisponivel(boolean disponivel) {

        this.disponivel = disponivel;
    }

}
