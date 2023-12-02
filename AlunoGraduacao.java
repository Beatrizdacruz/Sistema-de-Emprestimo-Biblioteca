public class AlunoGraduacao extends Aluno {
    public AlunoGraduacao(int codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }
}