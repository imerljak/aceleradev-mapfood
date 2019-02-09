package mapfood.model.mongodb;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("dados_entregas")
public class DadosEntrega {
  @Id 
  private String id;
  private String idEstabelecimento;
  private LocalDate dataSolicitacao;
  private Float distanciaEmKms;
  private Long tempoEstimadoEntregaEmMinutos;
  
  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdEstabelecimento() {
		return idEstabelecimento;
	}
	public void setIdEstabelecimento(String idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public Float getDistanciaEmKms() {
		return distanciaEmKms;
	}
	public void setDistanciaEmKms(Float distanciaEmKms) {
		this.distanciaEmKms = distanciaEmKms;
	}
	public Long getTempoEstimadoEntregaEmMinutos() {
		return tempoEstimadoEntregaEmMinutos;
	}
	public void setTempoEstimadoEntregaEmMinutos(Long tempoEstimadoEntregaEmMinutos) {
		this.tempoEstimadoEntregaEmMinutos = tempoEstimadoEntregaEmMinutos;
	}

  
}
