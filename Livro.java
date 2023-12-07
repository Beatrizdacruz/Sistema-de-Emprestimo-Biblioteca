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
    private int codigoExemplar;

    private List<Reserva> reservas;
    private boolean reservado;

    private List<Observador> observadores = new ArrayList<>();
    private Exemplar[] exemplares;
    private Biblioteca biblioteca;

    public Livro(int codigo, String titulo, String editora, String[] autores, int edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.reservas = new ArrayList<>();
        this.exemplares = new Exemplar[exemplaresDisponiveis];  // Inicializa o array de exemplares
        //inicializarExemplares();
    }



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
        }
        this.reservado = false;
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
                System.out.println("código do livro encontrado:" + livro.getCodigo());
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


    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Título do Livro: ").append(titulo).append("\n");
        details.append("Quantidade de Reservas: ").append(reservas.size()).append("\n");

        for (Reserva reserva : reservas) {
            details.append("Reserva por: ").append(reserva.getUsuario().getNome()).append("\n");
        }

        for (Exemplar exemplar : exemplares) {
            details.append("Código do Exemplar: ").append(exemplar.getCodigoExemplar()).append("\n");
            details.append("Status: ").append(exemplar.isDisponivel() ? "Disponível" : "Emprestado").append("\n");

            if (!exemplar.isDisponivel()) {
                Emprestimo emprestimo = biblioteca.encontrarEmprestimoPorExemplar(exemplar);
                if (emprestimo != null) {
                    details.append("Usuário do Empréstimo: ").append(emprestimo.getUsuario().getNome()).append("\n");
                    details.append("Data do Empréstimo: ").append(emprestimo.getDataEmprestimo()).append("\n");
                    details.append("Data Prevista para Devolução: ").append(emprestimo.getDataDevolucao()).append("\n");
                }
            }
        }

        return details.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplar exemplar = (Exemplar) o;

        return codigoExemplar == exemplar.getCodigoExemplar();
    }
}