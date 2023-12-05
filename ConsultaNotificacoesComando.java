public class ConsultaNotificacoesComando implements Comando {
    private Biblioteca biblioteca;

    public ConsultaNotificacoesComando(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void executar(String[] parametros) {
        if (parametros.length < 2) {
            System.out.println("Comando 'ntf' requer o código do professor.");
            return;
        }

        int codigoProfessor = Integer.parseInt(parametros[1]);
        int notificacoes = biblioteca.consultarNotificacoesProfessor(codigoProfessor);
        System.out.println("O professor foi notificado " + notificacoes + " vezes.");
    }
}
