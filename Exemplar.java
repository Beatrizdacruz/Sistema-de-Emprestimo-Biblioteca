import java.util.Date;

public class Exemplar {
    private int codigo;
    private Livro livro;
    private boolean disponivel;
    private Date dataEmprestimo;

    public Exemplar(int codigo, Livro livro) {
        this.codigo = codigo;
        this.livro = livro;
        this.disponivel = true;
        this.dataEmprestimo = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public Livro getLivro() {
        return livro;
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
}
