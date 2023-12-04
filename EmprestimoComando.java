class EmprestimoComando implements Comando {
    private Biblioteca biblioteca;

    public EmprestimoComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length != 3) {
            System.out.println("Comando de empréstimo inválido. Use o formato: emp código_usuario código_livro");
            return;
        }

        String codigo = parametros[0] + parametros[1] + parametros[2];

        String comando = parametros[0];
        String codigoUsuario = parametros[1];
        String codigoLivro = parametros[2];

        biblioteca.emprestarLivro(comando, codigoUsuario,codigoLivro);
    }
}