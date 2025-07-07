import java.util.Objects;

public class Rota {
    private final Cidade destino;
    private final int distancia;

    public Rota(Cidade destino, int distancia) {
        this.destino = Objects.requireNonNull(destino, "Destino não pode ser nulo");
        this.distancia = distancia;
    }

    public Cidade getDestino() { return destino; }
    public int getDistancia() { return distancia; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rota)) return false;
        Rota rota = (Rota) o;
        return distancia == rota.distancia && 
               destino.equals(rota.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destino, distancia);
    }

    @Override
    public String toString() {
        return String.format(" %s (%d km)", destino.getNome(), distancia);
    }
}