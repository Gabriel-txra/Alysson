import java.util.*;

public class MapaCidades {
    private final TreeSet<Cidade> cidades = new TreeSet<>();
    private final Map<Cidade, Set<Rota>> rotas = new HashMap<>();

    public void adicionarCidade(Cidade cidade) {
        Objects.requireNonNull(cidade, "Cidade não pode ser nula");
        if (!cidades.add(cidade)) {
            throw new IllegalArgumentException("Cidade " + cidade.getNome() + " já existe");
        }
        rotas.put(cidade, new HashSet<>());
    }

    public void conectarCidades(Cidade origem, Cidade destino, int distancia) {
        validarConexao(origem, destino, distancia);
        
        rotas.get(origem).add(new Rota(destino, distancia));
        rotas.get(destino).add(new Rota(origem, distancia));
    }

    private void validarConexao(Cidade origem, Cidade destino, int distancia) {
        Objects.requireNonNull(origem, "Origem não pode ser nula");
        Objects.requireNonNull(destino, "Destino não pode ser nulo");
        
        if (!cidades.contains(origem)) {
            throw new IllegalArgumentException(origem.getNome() + " não cadastrada");
        }
        if (!cidades.contains(destino)) {
            throw new IllegalArgumentException(destino.getNome() + " não cadastrada");
        }
        if (origem.equals(destino)) {
            throw new IllegalArgumentException("Não pode conectar uma cidade a ela mesma");
        }
        if (distancia <= 0) {
            throw new IllegalArgumentException("Distância deve ser positiva");
        }
    }

    public Set<Rota> getConexoes(Cidade cidade) {
        if (!cidades.contains(cidade)) {
            throw new IllegalArgumentException("Cidade não encontrada");
        }
        return Collections.unmodifiableSet(rotas.get(cidade));
    }

    public void listarConexoes(Cidade cidade) {
        Set<Rota> conexoes = getConexoes(cidade);
        if (conexoes.isEmpty()) {
            System.out.println(cidade.getNome() + " não possui conexões");
        } else {
            System.out.println("Conexões de " + cidade.getNome() + ":");
            conexoes.forEach(System.out::println);
        }
    }

    public boolean existeCaminho(Cidade origem, Cidade destino) {
        if (!cidades.contains(origem) || !cidades.contains(destino)) {
            return false;
        }
        if (origem.equals(destino)) {
            return true;
        }
        
        Set<Cidade> visitadas = new HashSet<>();
        return buscarCaminho(origem, destino, visitadas);
    }

    private boolean buscarCaminho(Cidade atual, Cidade destino, Set<Cidade> visitadas) {
        if (atual.equals(destino)) return true;
        visitadas.add(atual);
        
        for (Rota rota : rotas.getOrDefault(atual, Collections.emptySet())) {
            if (!visitadas.contains(rota.getDestino())) {
                if (buscarCaminho(rota.getDestino(), destino, visitadas)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void listarCidadesSemConexao() {
        long count = cidades.stream()
                          .filter(c -> rotas.getOrDefault(c, Collections.emptySet()).isEmpty())
                          .peek(c -> System.out.println("- " + c.getNome()))
                          .count();
        
        if (count == 0) {
            System.out.println("Todas as cidades têm conexões");
        }
    }

    public Set<Cidade> getCidades() {
        return Collections.unmodifiableSet(cidades);
    }
}