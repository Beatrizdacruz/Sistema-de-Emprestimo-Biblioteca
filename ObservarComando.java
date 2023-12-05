public class ObservarComando implements Comando {
    private Biblioteca biblioteca;

    public ObservarComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length < 3) {
            System.out.println("Comando 'obs' requer  código_usuario código_livro.");
            return;
        }

        String codUsuario = parametros[1];
        String codLivro = parametros[2];

        biblioteca.adicionarObservadorAoLivro(Integer.parseInt(codUsuario), Integer.parseInt(codLivro));
    }
}
