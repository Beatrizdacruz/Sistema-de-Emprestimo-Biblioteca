public class AlunoPosGraduacao extends Aluno {
    public AlunoPosGraduacao(int codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 4;
    }
}