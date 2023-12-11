import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Usuario implements Observador{
    private int codigo;
    private String nome;
    Date dataDevolucaoDevedor;
    int quantidadeReservas;
    private List<Emprestimo> emprestimos;
    private List<Reserva> reservas;

    public Usuario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataDevolucaoDevedor = null;
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();

    }


    public boolean isDevedor() {
        return dataDevolucaoDevedor != null && dataDevolucaoDevedor.before(new Date());
    }

    public void setDevedor(boolean devedor) {
        if (devedor) {
            // Define a data de devolução devedor para 7 dias no futuro
            this.dataDevolucaoDevedor = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
        } else {
            this.dataDevolucaoDevedor = null;
        }
    }
    public static Usuario encontrarUsuarioPorCodigo(List<Usuario> usuarios, int codigoUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                System.out.println("Código do usuário encontrado:" + codigoUsuario);
                return usuario;
            }
        }
        return null;
    }
    public void adicionarReserva(Reserva reserva) {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        reservas.add(reserva);
    }

    public int getQuantidadeReservas() {
        return quantidadeReservas;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Emprestimo[] getEmprestimos() {
        if (emprestimos == null) {
            System.out.println("usuario sem emprestimos");
            return new Emprestimo[0];
        }
        return emprestimos.toArray(new Emprestimo[0]);
    }


    public Reserva[] getReservas() {
        if (reservas == null) {
            return new Reserva[0];
        }
        return reservas.toArray(new Reserva[0]);
    }

    private RegraDeEmprestimo regraDeEmprestimo;

    public void setRegraEmprestimo(RegraDeEmprestimo regraEmprestimo) {
        this.regraDeEmprestimo = regraEmprestimo;
    }

    public void realizarEmprestimo(Livro livro, Exemplar exemplar) {
        if (regraDeEmprestimo != null) {
            regraDeEmprestimo.realizarEmprestimo(this, livro, exemplar);
        }
    }
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        if (emprestimos == null) {
            emprestimos = new ArrayList<>();
        }
        emprestimos.add(emprestimo);
    }

    @Override
    public void notificar(Livro livro) {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) obj;
        return codigo == usuario.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}