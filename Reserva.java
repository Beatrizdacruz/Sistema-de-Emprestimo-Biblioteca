import java.util.Date;
import java.util.List;

public class Reserva {
    private Livro livro;
    private Usuario usuario;
    private Date dataReserva;
    private List<Exemplar> exemplares;

    public Reserva(Usuario usuario, Livro livro, List<Exemplar> exemplares){
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = new Date();
        this.exemplares = exemplares;
    }
    public Date getDataReserva() {
        return dataReserva;
    }

    public boolean temReservaPendente() {
        Exemplar exemplar = encontrarExemplarPorCodigo(livro.getCodigo());
        return exemplar != null && exemplar.isDisponivel();
    }

    public void cancelarReserva() {
        Exemplar exemplar = encontrarExemplarPorCodigo(livro.getCodigo());
        if (exemplar != null) {
            exemplar.setDisponivel(true);
            exemplares.remove(exemplar);
            System.out.println("Reserva cancelada com sucesso para o livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome());
        } else {
            System.out.println("Nenhum exemplar disponível para cancelar a reserva.");
        }
    }

    private Exemplar encontrarExemplarPorCodigo(int codigoLivro) {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getCodigoLivro() == codigoLivro && exemplar.isDisponivel()) {
                return exemplar;
            }
        }
        return null;
    }


    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
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
}
