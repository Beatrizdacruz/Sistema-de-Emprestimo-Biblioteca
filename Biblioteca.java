import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> operacoesEmprestimo;
    private ArrayList<Reserva> reservas;

    public void emprestarLivro(String comando) {
        String[] parametros = comando.split(" ");

        if (parametros.length != 3 || !parametros[0].equals("emp")) {
            System.out.println("Comando inválido. Use o formato: emp <código_usuario> <código_livro>");
            return;
        }

        int codigoUsuario = Integer.parseInt(parametros[1]);
        int codigoLivro = Integer.parseInt(parametros[2]);

        Livro livro = Livro.encontrarLivroPorCodigo(livros, codigoLivro);

        Usuario usuario = Usuario.encontrarUsuarioPorCodigo(usuarios, codigoUsuario);


        if (usuario == null || livro == null) {
            System.out.println("Emprestimo não realizado. Usuário ou livro não encontrado.");
            return;
        }

        /*
        if (temReservaPendente(usuario, livro)) {
            cancelarReserva(usuario, livro);
        } */
        Emprestimo dataDevolucao = new Emprestimo();

        if (dataDevolucao.calcularDataDevolucao(usuario) != null) {
            Emprestimo operacao = new Emprestimo();
            operacao.setLivro(livro);
            operacao.setUsuario(usuario);
            operacao.setDataEmprestimo(new Date());
            operacao.setDataDevolucao(dataDevolucao.calcularDataDevolucao(usuario));
            operacoesEmprestimo.add(operacao);

            livro.setExemplaresDisponiveis(livro.getExemplaresDisponiveis() - 1);

            System.out.println("Empresto realizado com sucesso. Livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome());
        } else {
            System.out.println("Empresto não realizado. Não foi possível calcular a data de devolução.");
        }
    }

    // CRIAR temReservaPendente, cancelarReserva


}
