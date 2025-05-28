public class Aluno {
    private String Matricula;
    private String Nome;
    private String Curso;
    private String email;

    public Aluno(String Matricula, String Nome, String Curso, String email) {
        this.Matricula = Matricula;
        this.Nome = Nome;
        this.Curso = Curso;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Aluno{" +
                "Matricula='" + Matricula + '\'' +", Nome='" + Nome + '\'' +", Curso='" + Curso + '\'' +", email='" + email + '\'' +'}';
    }
}
