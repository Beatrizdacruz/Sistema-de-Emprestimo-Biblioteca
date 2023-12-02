import java.util.List;
import java.util.Date;

public class Usuario {
    private int codigo;
    private String nome;
    Date dataDevolucaoDevedor;

    public Usuario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataDevolucaoDevedor = null;

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
        System.out.println(codigoUsuario);
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                System.out.println("Código do usuário encontrado:" + codigoUsuario);
                return usuario;
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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
