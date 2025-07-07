import java.util.Objects;

public class Cidade implements Comparable<Cidade> {
    private final String nome;
    private final String estado;
    private final int populacao;

    public Cidade(String nome, String estado, int populacao) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.estado = Objects.requireNonNull(estado, "Estado não pode ser nulo");
        this.populacao = populacao;
    }

    // Construtor para cidades sem estado
    public Cidade(String nome, int populacao) {
        this(nome, "", populacao);
    }

    public String getNome() { return nome; }
    public String getEstado() { return estado; }
    public int getPopulacao() { return populacao; }

    @Override
    public int compareTo(Cidade outra) {
        int cmpNome = this.nome.compareToIgnoreCase(outra.nome);
        if (cmpNome != 0) return cmpNome;
        return this.estado.compareToIgnoreCase(outra.estado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade)) return false;
        Cidade cidade = (Cidade) o;
        return nome.equalsIgnoreCase(cidade.nome) && 
               estado.equalsIgnoreCase(cidade.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase(), estado.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%s%s (Pop: %d)", 
               nome, 
               estado.isEmpty() ? "" : " - " + estado, 
               populacao);
    }
}