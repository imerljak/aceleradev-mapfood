package mapfood.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import mapfood.model.jpa.Posicao;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "trechos"
})
public class Rota {

    @JsonProperty
    private List<Trecho> trechos = new ArrayList<>();

    public void adicionarTrecho(Trecho trecho) {
        trechos.add(trecho);
    }

    @JsonIgnore
    public long getDistanciaTotal() {
        return trechos
                .parallelStream()
                .mapToLong(Trecho::getDistanciaEmMetros)
                .sum();
    }

    @JsonIgnore
    public long getDurataoTotalEmSegundos() {
        return trechos
                .parallelStream()
                .mapToLong(Trecho::getDuracaoEmSegundos)
                .sum();
    }

    public Rota merge(Rota toMerge) {
        trechos.addAll(toMerge.trechos);
        return this;
    }

    @JsonPropertyOrder({
            "distanciaEmMetros",
            "duracaoEmSegundos",
            "inicio",
            "fim",
    })
    public static class Trecho {
        private long distanciaEmMetros;
        private long duracaoEmSegundos;
        private Posicao inicio;
        private Posicao fim;

        public long getDistanciaEmMetros() {
            return distanciaEmMetros;
        }

        public void setDistanciaEmMetros(long distanciaEmMetros) {
            this.distanciaEmMetros = distanciaEmMetros;
        }

        public long getDuracaoEmSegundos() {
            return duracaoEmSegundos;
        }

        public void setDuracaoEmSegundos(long duracaoEmSegundos) {
            this.duracaoEmSegundos = duracaoEmSegundos;
        }

        public Posicao getInicio() {
            return inicio;
        }

        public void setInicio(Posicao inicio) {
            this.inicio = inicio;
        }

        public Posicao getFim() {
            return fim;
        }

        public void setFim(Posicao fim) {
            this.fim = fim;
        }
    }
}
