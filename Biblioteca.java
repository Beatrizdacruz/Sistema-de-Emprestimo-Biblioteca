import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

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

    public void adicionarEmprestimo(Emprestimo emprestimo) {emprestimos.add(emprestimo);}

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
            return;
        }

        int codigoUsuarioInt = Integer.parseInt(codigoUsuario);
        int codigoLivroInt = Integer.parseInt(codigoLivro);

        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuarioInt);
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivroInt);
        Exemplar exemplar = Exemplar.encontrarExemplarPorCodigo(exemplares, codigoLivroInt);
        Reserva reserva = new Reserva(usuario, livro, exemplares);

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

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            Date dataDevolucao = calendar.getTime();

            // Crie o empréstimo com a data de devolução
            Emprestimo emprestimo = new Emprestimo(this, livro, exemplar, usuario, dataDevolucao);

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
            adicionarEmprestimo(emprestimo);
        }

        // Regras específicas para Professores
        else if (usuario instanceof Professor) {
            // Defina a data de devolução como duas semanas a partir de agora para professores
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, 2);
            Date dataDevolucao = calendar.getTime();

            // Crie o empréstimo com a data de devolução
            Emprestimo emprestimo = new Emprestimo(this, livro, exemplar, usuario, dataDevolucao);
            emprestimo.realizarEmprestimo(usuario, livro, exemplar);
            adicionarEmprestimo(emprestimo);
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

        if (usuario == null || livro == null) {
            System.out.println("Devolução não realizada. Usuário ou livro não encontrado.");
            return;
        }

        // Verifica se existe empréstimo em aberto para aquele livro e usuário
        if (emprestimo == null) {
            System.out.println("Devolução não realizada. Nenhum empréstimo em aberto encontrado.");
            return;
        }

        // Realiza a devolução
        emprestimo.realizarDevolucao();


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

        // Adiciona o professor como observador do livro
        ObservadorProfessor observadorProfessor = new ObservadorProfessor();
        livro.registrarObservador(observadorProfessor);

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

    // Na classe Biblioteca
    public void consultarLivro(int codigoLivro) {
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivro);

        if (livro != null) {
            System.out.println(livro.toString());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    // Na classe Biblioteca
    public Emprestimo encontrarEmprestimoPorExemplar(Exemplar exemplar) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getExemplar().equals(exemplar) && !emprestimo.isDevolvido()) {
                return emprestimo;
            }
        }
        return null;
    }

    public void adicionarObservadorAoLivro(int codigoUsuario, int codigoLivro) {
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivro);
        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuario);

        if (livro != null && usuario != null) {
            ObservadorProfessor observadorProfessor = new ObservadorProfessor();
            livro.registrarObservador(observadorProfessor);
            System.out.println("Professor registrado como observador para o livro.");
        } else {
            System.out.println("Livro ou usuário não encontrado.");
        }
    }


    public void consultarUsuario(int codigoUsuario) {
        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuario);

        if (usuario != null) {
            System.out.println("Empréstimos do Usuário " + usuario.getNome() + ":");
            for (Emprestimo emprestimo : usuario.getEmprestimos()) {
                System.out.println(emprestimo);
                System.out.println("Título do Livro: " + emprestimo.getLivro().getTitulo());
                System.out.println("Data do Empréstimo: " + emprestimo.getDataEmprestimo());
                System.out.println("Status: " + (emprestimo.getDataDevolucao().before(new Date())));
                System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
            }

            System.out.println("Reservas do Usuário " + usuario.getNome() + ":");
            for (Reserva reserva : usuario.getReservas()) {
                System.out.println("Título do Livro Reservado: " + reserva.getLivro().getTitulo());
                System.out.println("Data da Reserva: " + reserva.getDataReserva());
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public int consultarNotificacoesProfessor(int codigoProfessor) {
        ObservadorProfessor observadorProfessor = new ObservadorProfessor();

        // Adicionar o observadorProfessor à lista de observadores de livros
        for (Livro livro : livros) {
            livro.registrarObservador(observadorProfessor);
        }

        // Realizar notificações (simulação para o exemplo)
        for (Livro livro : livros) {
            livro.verificarReservas();
        }

        return observadorProfessor.getNotificacoes();
    }


}


