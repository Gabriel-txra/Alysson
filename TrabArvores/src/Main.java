public class Main {
    public static void main(String[] args) {
        GerenciadorFeira feira = new GerenciadorFeira();

        
        feira.cadastrarLivro("Java Básico", "Ana Silva", 2015);
        feira.cadastrarLivro("Algoritmos", "Carlos Souza", 2018);
        feira.cadastrarLivro("Programação Java", "Ana Silva", 2019);

        feira.exibirTodosOsLivros();
        feira.exibirAutoresELivros();
    }
}
