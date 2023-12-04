class DevolucaoComando implements Comando {
    private Biblioteca biblioteca;

    public DevolucaoComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length != 3) {
            System.out.println("Comando de devolução inválido. Use o formato: dev código_usuario código_livro");
            return;
        }

        String comando = parametros[0];
        String codigoUsuario = parametros[1];
        String codigoLivro = parametros[2];

        biblioteca.devolverLivro(comando, codigoUsuario,codigoLivro);
        //System.out.println(resultado);
    }
}