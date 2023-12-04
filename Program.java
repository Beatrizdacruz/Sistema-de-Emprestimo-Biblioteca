import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Realizando operações na biblioteca
        Map<String, Comando> comandos = new HashMap<>();
        comandos.put("emp", new EmprestimoComando(biblioteca));
        comandos.put("dev", new DevolucaoComando(biblioteca));
        comandos.put("res", new ReservaComando(biblioteca));

        String pedido = scanner.nextLine().trim();

        String[] parametros = pedido.split(" ");
        System.out.println(parametros[0]);
        System.out.println(parametros[1]);
        System.out.println(parametros[2]);
        processarPedido(comandos, pedido);


        scanner.close();
    }

    private static void processarPedido(Map<String, Comando> comandos, String pedido) {
        String[] parametros = pedido.split(" ");

        if (parametros.length < 1) {
            System.out.println("Comando inválido. Digite um comando válido.");
            return;
        }

        String comando = parametros[0];
        Comando executor = comandos.get(comando);
        if (executor != null) {
            executor.executar(parametros);
        } else {
            System.out.println("Comando inválido. Digite um comando válido.");
        }
    }
}



