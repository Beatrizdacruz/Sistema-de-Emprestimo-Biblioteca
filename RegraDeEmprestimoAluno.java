public class RegraDeEmprestimoAluno implements RegraDeEmprestimo {
    @Override
        public void realizarEmprestimo (Usuario usuario, Livro livro, Exemplar exemplar){
            if (usuario instanceof Aluno) {
                Aluno aluno = (Aluno) usuario;

                // Adicionar lógica específica para empréstimo de aluno, se necessário
                //ex: verificar limites, reservas, etc.

                exemplar.emprestar();
                System.out.println("Empréstimo realizado para aluno: " + aluno.getNome() +
                        ", Livro: " + livro.getTitulo() +
                        ", Exemplar: " + exemplar.getCodigoExemplar());
            } else {
                System.out.println("A estratégia de empréstimo de aluno não se aplica a este usuário.");
            }
        }

}