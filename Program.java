import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando instância da biblioteca
        Biblioteca biblioteca = new Biblioteca();
        String pedido = scanner.nextLine();

        // Realizando operações na biblioteca
        biblioteca.emprestarLivro(pedido);

    }
}
