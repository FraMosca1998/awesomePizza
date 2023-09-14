package it.awesome.pizza.database.documents;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ORDER_TRACKING")
public class OrderTracking implements Serializable {

	private static final long serialVersionUID = 1872743173004304476L;
    
	@Id
	private String id;
	
	@Field("STATUS")
	private String status;

	@Field("DT_INIZIO")
	private String dtInizio;
	
	@Field("DT_FINE")
	private String dtFine;
	
	@Field("TICKET_NUMBER")
	private Integer ticketNumber;
	
	@Field("ORDER_CODE")
	private String orderCode;
	
	@Field("ORDER_DETAILS")
	private OrderDetails orderDatails;

	
	public OrderTracking() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDtInizio() {
		return dtInizio;
	}


	public void setDtInizio(String dtInizio) {
		this.dtInizio = dtInizio;
	}


	public String getDtFine() {
		return dtFine;
	}


	public void setDtFine(String dtFine) {
		this.dtFine = dtFine;
	}


	public Integer getTicketNumber() {
		return ticketNumber;
	}


	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}


	public OrderDetails getOrderDatails() {
		return orderDatails;
	}


	public void setOrderDatails(OrderDetails orderDatails) {
		this.orderDatails = orderDatails;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public String toString() {
		return "OrderTracking [id=" + id + ", status=" + status + ", dtInizio=" + dtInizio + ", dtFine=" + dtFine
				+ ", ticketNumber=" + ticketNumber + ", orderCode=" + orderCode + ", orderDatails=" + orderDatails
				+ "]";
	}
	
}