package it.awesome.pizza.apis.requests;

import java.io.Serializable;
import java.util.List;

public class PlaceOrderRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3977267310685614161L;
	private String nomeCliente;
	private List<String> pizze;
	
	public PlaceOrderRequest() {
		super();
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public List<String> getPizze() {
		return pizze;
	}

	public void setPizze(List<String> pizze) {
		this.pizze = pizze;
	}

	@Override
	public String toString() {
		return "PlaceOrderRequest [nomeCliente=" + nomeCliente + ", pizze=" + pizze + "]";
	}
}
