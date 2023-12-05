public class ObservadorProfessor implements Observador {
    private int notificacoes = 0;

    @Override
    public void notificar(Livro livro) {
        System.out.println("Livro com mais de duas reservas simultâneas: " + livro.getTitulo());
        notificacoes++;
    }

    public int getNotificacoes() {
        return notificacoes;
    }
}