import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class Emprestimo{
    private Livro livro;
    private Exemplar exemplar;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Biblioteca biblioteca;

    public Emprestimo(Biblioteca biblioteca, Livro livro, Exemplar exemplar, Usuario usuario) {
        this.biblioteca = biblioteca;
        this.livro = livro;
        this.exemplar = exemplar;
        this.usuario = usuario;
    }

    public Date calcularDataDevolucao(Usuario usuario) {
        int diasParaDevolucao = 14; // Padrão de 14 dias
        if (usuario instanceof AlunoGraduacao) {
            diasParaDevolucao = 7;
        } else if (usuario instanceof AlunoPosGraduacao) {
            diasParaDevolucao = 10;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, diasParaDevolucao);

        return calendar.getTime();
    }

    public void realizarEmprestimo(Usuario usuario, Livro livro, Exemplar exemplar) {
        Date dataDevolucao = calcularDataDevolucao(usuario);

        if (dataDevolucao != null) {
            this.livro = livro;
            this.usuario = usuario;
            this.dataEmprestimo = new Date();
            this.dataDevolucao = dataDevolucao;

            exemplar.setDisponivel(false);
            biblioteca.adicionarEmprestimo(this);

            System.out.println("Empresto realizado com sucesso. Livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome());
        } else {
            System.out.println("Empresto não realizado. Não foi possível calcular a data de devolução.");
        }
    }
    public static Emprestimo encontrarEmprestimoPorUsuarioEExemplar(ArrayList<Emprestimo> emprestimos, Usuario usuario, Exemplar exemplar) {
        for (Emprestimo emprestimo : emprestimos) {
            //System.out.println("emprestimo tal:" + emprestimo.getUsuario() + " exemplar " + emprestimo.getExemplar());
            if (emprestimo.getUsuario().equals(usuario)) {
                //System.out.println("emprestimo encontrado:" + emprestimo);
                return emprestimo;
            }
        }
        return null;
    }

    public void realizarDevolucao() {
        if (!this.isDevolvido()) {
            this.exemplar.setDisponivel(true);
            this.setDataDevolucao(new Date());
            System.out.println("Devolução realizada com sucesso. Usuário: " + usuario.getNome() +
                    ", Livro: " + livro.getTitulo());
        } else {
            System.out.println("Este empréstimo já foi devolvido anteriormente.");
        }
    }

    public boolean isDevolvido() {
        return this.dataDevolucao == null;
    }

    public int emprestimosEmAndamento(Aluno aluno) {
        // Lógica para contar a quantidade de empréstimos em andamento para o aluno
        return 0;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public Exemplar getExemplar() {
        return exemplar;
    }
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

}