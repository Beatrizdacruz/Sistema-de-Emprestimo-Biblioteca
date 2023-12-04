public class SairComando implements Comando {
    @Override
    public void executar(String[] parametros) {
        System.out.println("Saindo do sistema. Até logo!");
        System.exit(0);
    }
}
