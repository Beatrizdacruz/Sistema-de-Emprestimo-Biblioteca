import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        // Adicionando usuários à lista
        listaDeUsuarios.add(new AlunoGraduacao(123, "João da Silva"));
        listaDeUsuarios.add(new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"));
        listaDeUsuarios.add(new AlunoGraduacao(789, "Pedro Paulo"));
        listaDeUsuarios.add(new Professor(100, "Carlos Lucena"));


        Scanner scanner = new Scanner(System.in);

        // Criando instância da biblioteca
        Biblioteca biblioteca = new Biblioteca();
        String pedido = scanner.nextLine();

        // Realizando operações na biblioteca
        biblioteca.emprestarLivro(pedido);

    }
}
