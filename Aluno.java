public abstract class Aluno extends Usuario {
    public Aluno(int codigo, String nome) {
        super(codigo, nome);
    }

    public abstract int getLimiteEmprestimos();
}




