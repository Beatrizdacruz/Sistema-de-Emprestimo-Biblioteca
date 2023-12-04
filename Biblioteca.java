import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private static Biblioteca instancia = null;
    private ArrayList<Livro> livros;
    private ArrayList<Exemplar> exemplares;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Reserva> reservas;

    private Biblioteca() {
        this.livros = new ArrayList<>();
        this.exemplares = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public static Biblioteca getInstance() {
        if (instancia == null) {
            instancia = new Biblioteca();
        }
        return instancia;
    }

    public void adicionarUsuarios(List<Usuario> novosUsuarios) {
        this.usuarios.addAll(novosUsuarios);
    }

    public void adicionarLivros(List<Livro> novosLivros) {
        this.livros.addAll(novosLivros);
    }

    public void adicionarExemplares(List<Exemplar> novosExemplares) {
        this.exemplares.addAll(novosExemplares);
    }

    public void emprestarLivro(String comando, String codigoUsuario, String codigoLivro) {

        if (!comando.equals("emp")) {
            System.out.println("Comando inválido para empréstimo.");
        }

        int codigoUsuarioInt = Integer.parseInt(codigoUsuario);
        int codigoLivroInt = Integer.parseInt(codigoLivro);

        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuarioInt);
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivroInt);
        Exemplar exemplar = Exemplar.encontrarExemplarPorCodigo(exemplares, codigoLivroInt);
        Reserva reserva = new Reserva(usuario, livro, exemplares);
        Emprestimo emprestimo = new Emprestimo();

        if (usuario == null || livro == null || exemplar == null) {
            System.out.println("Emprestimo não realizado. Usuário, livro ou exemplar não encontrado.");
            return;
        }

        // Verifica se o usuário está "devedor" de um livro em atraso
        if (usuario.isDevedor()) {
            System.out.println("Emprestimo não realizado. Usuário está devedor de um livro em atraso.");
            return;
        }

        // Verifica se o exemplar está disponível
        if (!exemplar.isDisponivel()) {
            System.out.println("Emprestimo não realizado. Exemplar não disponível.");
            return;
        }

        // Regras específicas para Alunos de Graduação e Pós-Graduação
        if (usuario instanceof Aluno) {
            Aluno aluno = (Aluno) usuario;

            // Verifica limite de empréstimos em aberto
            if (emprestimo.emprestimosEmAndamento(aluno) >= aluno.getLimiteEmprestimos()) {
                System.out.println("Emprestimo não realizado. Limite de empréstimos em aberto atingido.");
                return;
            }

            // Verifica reservas
            if (reserva.temReservaPendente()) {
                reserva.cancelarReserva();
            }

            emprestimo.realizarEmprestimo(usuario, livro, exemplar);
        }

        // Regras específicas para Professores
        else if (usuario instanceof Professor) {
            emprestimo.realizarEmprestimo(usuario, livro, exemplar);
        }
    }

    public void devolverLivro(String comando, String codUsuario, String codLivro) {

        if (!comando.equals("dev")) {
            System.out.println("Comando inválido para devolver.");
        }

        int codigoUsuario = Integer.parseInt(codUsuario);
        int codigoLivro = Integer.parseInt(codLivro);

        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuario);
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivro);
        Exemplar exemplar = Exemplar.encontrarExemplarPorCodigo(exemplares, codigoLivro);
        Emprestimo emprestimo = Emprestimo.encontrarEmprestimoPorUsuarioEExemplar(emprestimos, usuario, exemplar);

        if (usuario == null || livro == null || exemplar == null) {
            System.out.println("Devolução não realizada. Usuário, livro ou exemplar não encontrado.");
            return;
        }

        // Verifica se existe empréstimo em aberto para aquele livro e usuário
        if (emprestimo == null) {
            System.out.println("Devolução não realizada. Nenhum empréstimo em aberto encontrado.");
            return;
        }

        // Realiza a devolução
        emprestimo.realizarDevolucao();

        System.out.println("Devolução realizada com sucesso. Usuário: " + usuario.getNome() +
                ", Livro: " + livro.getTitulo());
    }

    public String reservarLivro(String comando, String codUsuario, String codLivro) {
        if (!comando.equals("res")) {
            return "Comando inválido para reserva.";
        }

        int codigoUsuario = Integer.parseInt(codUsuario);
        int codigoLivro = Integer.parseInt(codLivro);

        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuario);
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivro);
        List<Exemplar> exemplaresLivro = encontrarExemplaresPorCodigoLivro(codigoLivro);
        Reserva reserva = new Reserva(usuario, livro, exemplaresLivro);

        if (usuario == null || livro == null || exemplaresLivro.isEmpty()) {
            return "Reserva não realizada. Usuário, livro ou exemplar não encontrado.";
        }

        // Verifica se o usuário já atingiu o limite de reservas
        if (usuario.getQuantidadeReservas() >= 3) {
            return "Reserva não realizada. Limite de reservas atingido para o usuário.";
        }

        // Verifica se o livro já está reservado
        if (livro.isReservado()) {
            return "Reserva não realizada. Livro já reservado por outro usuário.";
        }

        // Realiza a reserva
        reserva.realizarReserva();

        System.out.println("Reserva realizada com sucesso. Usuário: " + usuario.getNome() +
                ", Livro: " + livro.getTitulo());
        return comando;
    }

    private List<Exemplar> encontrarExemplaresPorCodigoLivro(int codigoLivro) {
        List<Exemplar> exemplaresLivro = new ArrayList<>();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getCodigoLivro() == codigoLivro && exemplar.isDisponivel()) {
                exemplaresLivro.add(exemplar);
            }
        }
        return exemplaresLivro;
    }


}


