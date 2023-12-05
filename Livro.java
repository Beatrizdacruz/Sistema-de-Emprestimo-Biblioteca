import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int codigo;
    private String titulo;
    private String editora;
    private String[] autores;
    private int edicao;
    private int anoPublicacao;
    private int exemplaresDisponiveis;

    private List<Reserva> reservas;
    private boolean reservado;

    private List<Observador> observadores = new ArrayList<>();

    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.notificar(this);
        }
    }

    // Método que verifica a condição de mais de duas reservas e notifica os observadores
    public void verificarReservas() {
        if (reservas.size() > 2) {
            notificarObservadores();
            this.reservado = true;
        }
        this.reservado = false;
    }

    public Livro(int codigo, String titulo, String editora, String[] autores, int edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.reservas = new ArrayList<>();
    }



    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
        verificarReservas();  //Adicionando uma reserva, verificando a condição para notificar observadores
    }

    public void removerReserva(Reserva reserva) {
        reservas.remove(reserva);
        verificarReservas();  // Removendo uma reserva, verificando a condição para notificar observadores
    }


    public static Livro encontrarLivroPorCodigo(List<Livro> livros, int codigoLivro) {
        for (Livro livro : livros) {
            if (livro.getCodigo() == codigoLivro) {
                System.out.println("código do livro encontrado:" + livro);
                return livro;
            }
        }
        return null;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getAutores() {
        return autores;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public void setExemplaresDisponiveis(int exemplaresDisponiveis) {
        this.exemplaresDisponiveis = exemplaresDisponiveis;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


}