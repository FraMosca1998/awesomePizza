package it.awesome.pizza.database.documents;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 243413089782796233L;
	
	@Id
	private String id;
	
	@Field("NOME_CLIENTE")
	private String nomeCliente;
	
	@Field("PIZZE")
	private List<String> pizze;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getPizze() {
		return pizze;
	}

	public void setPizze(List<String> pizze) {
		this.pizze = pizze;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", nomeCliente=" + nomeCliente + ", pizze=" + pizze + "]";
	}
}