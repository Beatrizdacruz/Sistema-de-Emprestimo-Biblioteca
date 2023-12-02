import java.util.List;

public class Usuario {
    private int codigo;
    private String nome;

    public Usuario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;

    }

    public static Usuario encontrarUsuarioPorCodigo(List<Usuario> usuarios, int codigoUsuario) {
        System.out.println(codigoUsuario);
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                System.out.println("Código do usuário encontrado:" + codigoUsuario);
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
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
