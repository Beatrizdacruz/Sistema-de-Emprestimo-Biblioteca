public class ReservaComando implements Comando {
    private Biblioteca biblioteca;

    public ReservaComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length != 3) {
            System.out.println("Comando de reserva inválido. Use o formato: res código_usuario código_livro");
            return;
        }

        String comando = parametros[0];
        String codigoUsuario = parametros[1];
        String codigoLivro = parametros[2];

        biblioteca.reservarLivro(comando, codigoUsuario,codigoLivro);
    }
}
