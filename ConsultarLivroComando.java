public class ConsultarLivroComando implements Comando {
    private Biblioteca biblioteca;

    public ConsultarLivroComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length < 2) {
            System.out.println("Comando 'liv' requer o código do livro.");
            return;
        }

        int codigoLivro = Integer.parseInt(parametros[1]);
        biblioteca.consultarLivro(codigoLivro);
    }
}
