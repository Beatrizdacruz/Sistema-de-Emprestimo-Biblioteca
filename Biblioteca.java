import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Exemplar> exemplares;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Reserva> reservas;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.exemplares = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
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

        if (usuario == null || livro == null || exemplar == null) {
            System.out.println("Emprestimo não realizado. Usuário, livro ou exemplar não encontrado.");
            return;
        }

        if (reserva.temReservaPendente()) {
            reserva.cancelarReserva();
        }

        Date dataDevolucao = calcularDataDevolucao(usuario);
        if (dataDevolucao != null) {
            Emprestimo operacao = new Emprestimo();
            operacao.setLivro(livro);
            operacao.setUsuario(usuario);
            operacao.setDataEmprestimo(new Date());
            operacao.setDataDevolucao(dataDevolucao);
            emprestimos.add(operacao);

            exemplar.setDisponivel(false);

            System.out.println("Emprestimo realizado com sucesso. Livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome());
        } else {
            System.out.println("Emprestimo não realizado. Não foi possível calcular a data de devolução.");
        }
    }


    private Date calcularDataDevolucao(Usuario usuario) {
        // Lógica para calcular a data de devolução com base no tipo de usuário
        // Implemente conforme necessário
        return new Date(); // Implemente a lógica real
    }


}
