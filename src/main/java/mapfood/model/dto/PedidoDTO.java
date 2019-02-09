package mapfood.model.dto;

import java.util.List;

import mapfood.model.mongodb.ProdutoEstabelecimento;

public class PedidoDTO {
	private  String clienteId;
	private List<ProdutoEstabelecimento> produto;
		
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public List<ProdutoEstabelecimento> getProduto() {
		return produto;
	}
	public void setProduto(List<ProdutoEstabelecimento> produto) {
		this.produto = produto;
	}
	
}
