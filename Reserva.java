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

    public void realizarReserva() {
        if (temReservaPendente()) {
            Exemplar exemplarReservado = exemplares.get(0);
            exemplarReservado.setDisponivel(false); // Marca o primeiro exemplar como indisponível

            // Adiciona a reserva à lista de reservas do usuário
            Reserva reserva = new Reserva(usuario, livro, exemplares);
            usuario.adicionarReserva(reserva);

            livro.setReservado(true);
            System.out.println("Reserva realizada com sucesso. Usuário: " + usuario.getNome() +
                    ", Livro: " + livro.getTitulo());
        } else {
            System.out.println("Nenhum exemplar disponível para realizar a reserva.");
        }
    }



    public Date getDataReserva() {
        return dataReserva;
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