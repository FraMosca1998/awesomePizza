package it.awesome.pizza.common.constants;

public interface Constants {

	public interface Headers { 
		String CORRELATION_ID = "CorrelationId";
	}

	public interface OrderStatus {
		String PLACED = "PLACED";
		String DELIVERED= "DELIVERED";
		String NO_ORDER="NO_ORDER";
	}
	
	public interface Query {
		String STATUS="STATUS";
		String DT_INIZIO="DT_INIZIO";
		String DT_FINE="DT_FINE";
		String TICKET_NUMBER="TICKET_NUMBER";
		String ORDER_CODE="ORDER_CODE";
	}
	
	public static final String AWESOME_PIZZA="AWESOME_PIZZA";
	public static final String UNDERSCORE="_";
	public static final String OK="OK";
	public static final String KO="KO";
	public static final String ELABORATO="ELABORATO";
	public static final String IN_ERRORE="IN_ERRORE";
	public static final String IN_ELABORAZIONE="IN_ELABORAZIONE";

}