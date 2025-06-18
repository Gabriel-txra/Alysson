import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class GerenciadorFeira {
    private TreeSet<Livro> todosOsLivros;
    private TreeMap<String, TreeSet<String>> autoresELivros;

    public GerenciadorFeira() {
        this.todosOsLivros = new TreeSet<>();
        this.autoresELivros = new TreeMap<>();
    }


    public void cadastrarLivro(String titulo, String autor, int ano) {
        Livro novoLivro = new Livro(titulo, autor, ano);
        todosOsLivros.add(novoLivro);
        cadastrarAutor(autor, titulo);
    }

  
     
    private void cadastrarAutor(String autor, String titulo) {
        
        autoresELivros.computeIfAbsent(autor, k -> new TreeSet<>()).add(titulo);
    }

    
    public void exibirTodosOsLivros() {
        System.out.println("Todos os livros:");
        if (todosOsLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (Livro livro : todosOsLivros) {
            System.out.println(livro);
        }
        System.out.println(); 
    }

    public void exibirAutoresELivros() {
        System.out.println("Autores e seus livros:");
        if (autoresELivros.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        for (Map.Entry<String, TreeSet<String>> entry : autoresELivros.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String titulo : entry.getValue()) {
                System.out.println("- " + titulo);
            }
            System.out.println();
        }
    }
}