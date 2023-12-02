import java.util.ArrayList;
import java.util.Date;
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

    public void emprestarLivro(String comando) {
        String[] parametros = comando.split(" ");

        if (parametros.length != 3 || !parametros[0].equals("emp")) {
            System.out.println("Comando inválido. Use o formato: emp código_usuario código_livro");
            return;
        }

        int codigoUsuario = Integer.parseInt(parametros[1]);
        int codigoLivro = Integer.parseInt(parametros[2]);

        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuario);
        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivro);
        Exemplar exemplar = Exemplar.encontrarExemplarPorCodigo(exemplares,codigoLivro);
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


    private Date calcularDataDevolucao(Usuario usuario) {
       //precisar criar a lógica, ainda.
        return new Date();
    }


}


