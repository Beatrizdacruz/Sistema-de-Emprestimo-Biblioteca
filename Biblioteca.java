import java.util.ArrayList;
import java.util.Date;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> operacoesEmprestimo;
    private ArrayList<Reserva> reservas;

    public Biblioteca() {
    }

    // Métodos para emprestar, devolver, reservar livros
    public void reservarLivro(Livro livro, Usuario usuario) {
        Reserva reserva = new Reserva();
        reserva.setLivro(livro);
        reserva.setUsuario(usuario);
        reserva.setDataReserva(new Date());
        reservas.add(reserva);
        System.out.println("Reserva realizada com sucesso!");
    }

}
