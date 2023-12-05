public class ConsultaUsuarioComando implements Comando {
    private Biblioteca biblioteca;

    public ConsultaUsuarioComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length < 2) {
            System.out.println("Comando de usu inválido. Use o formato: usu código_usuario");
            return;
        }

        int codigoUsuario = Integer.parseInt(parametros[1]);
        biblioteca.consultarUsuario(codigoUsuario);
    }
}
