import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        // Adicionando usuários à lista
        listaDeUsuarios.add(new AlunoGraduacao(123, "João da Silva"));
        listaDeUsuarios.add(new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"));
        listaDeUsuarios.add(new AlunoGraduacao(789, "Pedro Paulo"));
        listaDeUsuarios.add(new Professor(100, "Carlos Lucena"));

        List<Livro> listaDeLivros = new ArrayList<>();
        // Adicionando livros à lista
        listaDeLivros.add(new Livro(100, "Engenharia de Software", "Addison-Wesley", new String[]{"Ian Sommervile"}, 6, 2000));
        listaDeLivros.add(new Livro(101, "UML – Guia do Usuário", "Campus", new String[]{"Grady Booch", "James Rumbaugh", "Ivar Jacobson"}, 7, 2000));
        listaDeLivros.add(new Livro(200, "Code Complete", "Microsoft Press", new String[]{"Steve McConnell"}, 2, 2014));
        listaDeLivros.add(new Livro(201, "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", new String[]{"Robert Martin"}, 1, 2002));
        listaDeLivros.add(new Livro(300, "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional", new String[]{"Martin Fowler"}, 1, 1999));
        listaDeLivros.add(new Livro(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press", new String[]{"Norman Fenton", "James Bieman"}, 3, 2014));
        listaDeLivros.add(new Livro(400, "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", new String[]{"Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"}, 1, 1994));
        listaDeLivros.add(new Livro(401, "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", new String[]{"Martin Fowler"}, 3, 2003));

        List<Exemplar> listaDeExemplares = new ArrayList<>();
        listaDeExemplares.add(new Exemplar(100, 01));
        listaDeExemplares.add(new Exemplar(100, 02));
        listaDeExemplares.add(new Exemplar(101, 03));
        listaDeExemplares.add(new Exemplar(200, 04));
        listaDeExemplares.add(new Exemplar(201, 05));
        listaDeExemplares.add(new Exemplar(300, 06));
        listaDeExemplares.add(new Exemplar(300, 07));
        listaDeExemplares.add(new Exemplar(400, 8));
        listaDeExemplares.add(new Exemplar(400, 9));

        // Criando instância da biblioteca
        Biblioteca biblioteca = Biblioteca.getInstance();
        biblioteca.adicionarUsuarios(listaDeUsuarios);
        biblioteca.adicionarLivros(listaDeLivros);
        biblioteca.adicionarExemplares(listaDeExemplares);

        String pedido = scanner.nextLine();

        // Realizando operações na biblioteca
        biblioteca.emprestarLivro(pedido);

    }
}
