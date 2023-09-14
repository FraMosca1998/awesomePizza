package it.awesome.pizza.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.awesome.pizza.apis.requests.PlaceOrderRequest;
import it.awesome.pizza.common.constants.Constants;
import it.awesome.pizza.common.constants.Constants.OrderStatus;
import it.awesome.pizza.common.rest.GenericResponse;
import it.awesome.pizza.common.rest.Result;
import it.awesome.pizza.database.documents.OrderDetails;
import it.awesome.pizza.database.documents.OrderTracking;
import it.awesome.pizza.database.repository.OrderTrackingRepository;
import it.awesome.pizza.utils.AwesomePizzaUtils;

@Service
public class AwesomePizzaService {

	private static final Logger log = LoggerFactory.getLogger(AwesomePizzaService.class);
	@Autowired
	public MongoTemplate mongoTemplate;
	@Autowired
	public OrderTrackingRepository orderTrackingRepository;
	@Autowired
	public AwesomePizzaUtils awesomePizzaUtils;

	public GenericResponse<?> placeOrder(PlaceOrderRequest request) {
		GenericResponse<?> resp = new GenericResponse<>();
		try {
			Integer currentNumber = null;
			log.info("Procedo nel recuperare il numero ordine massimo presente in collection");
			OrderTracking lastOrder = lastOrderQuery();
			if (Objects.nonNull(lastOrder)) {
				currentNumber = lastOrder.getTicketNumber();
				currentNumber++;
			} else {
				currentNumber = 1;
			}
			String newTicketToSave = buildTicketNumber(request.getNomeCliente(), currentNumber);
			log.info("IL CLIENTE {}, HA IL SEGUENTE TICKET NUMBER: {}", request.getNomeCliente(), newTicketToSave);
			OrderTracking order = orderTrackingRepository
					.save(buildOrderTracking(newTicketToSave, request, currentNumber));
			Result resObj = new Result(Constants.OK, order.getOrderCode());
			resp.setResult(resObj);
			resp.setResultDescription(HttpStatus.OK.toString());
		} catch (Exception e) {
			log.error("Errore durante la business logic di placeOrder: {}", e.getMessage());
			return awesomePizzaUtils.buildKoResponse();
		}
		return resp;
	}

	private String buildTicketNumber(String nomeUtente, Integer currentNumber) {
		return Constants.AWESOME_PIZZA.concat(Constants.UNDERSCORE).concat(nomeUtente)
				.concat(Constants.UNDERSCORE.concat(currentNumber.toString()));
	}

	private OrderTracking lastOrderQuery() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, Constants.Query.TICKET_NUMBER));
		return mongoTemplate.findOne(query, OrderTracking.class);
	}
	
	private List<OrderTracking> allOrdersQuery() {
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.Query.STATUS).is(Constants.OrderStatus.PLACED));
		query.with(Sort.by(Sort.Direction.ASC, Constants.Query.TICKET_NUMBER));
		return mongoTemplate.find(query, OrderTracking.class);
	}

	private OrderTracking buildOrderTracking(String newTicketToSave, PlaceOrderRequest request, Integer ticketNumber) {
		OrderTracking tracking = new OrderTracking();
		tracking.setStatus(OrderStatus.PLACED);
		tracking.setTicketNumber(ticketNumber);
		tracking.setOrderCode(buildTicketNumber(request.getNomeCliente(), ticketNumber));
		tracking.setDtInizio(String.valueOf(LocalDateTime.now()));
		OrderDetails details = new OrderDetails();
		details.setNomeCliente(request.getNomeCliente());
		details.setPizze(request.getPizze());
		tracking.setOrderDatails(details);
		return tracking;
	}

	public GenericResponse<?> statusOrder(String orderCode) {
		GenericResponse<?> resp = new GenericResponse<>();
		try {
			log.info("Procedo ad effettuare la query per il seguente orderCode: {}", orderCode);
			List<OrderTracking> orderList = orderTrackingRepository.findByOrderCode(orderCode);
			if (!CollectionUtils.isEmpty(orderList)) {
				Result resObj = new Result(orderCode, orderList.get(0).getStatus());
				resp.setResult(resObj);
				resp.setResultDescription(HttpStatus.OK.toString());
			}
			else {
				log.info("Nessun ordine presente con questo orderCode: {}",orderCode);
				Result resObj = new Result(orderCode, Constants.OrderStatus.NO_ORDER);
				resp.setResult(resObj);
				resp.setResultDescription(HttpStatus.OK.toString());
			}

		} catch (Exception e) {
			log.error("Errore durante la business logic di statusOrder: {}", e.getMessage());
			return awesomePizzaUtils.buildKoResponse();
		}
		return resp;
	}
	
	public GenericResponse<?> deliverOrder(List<String> orderCodes) {
		GenericResponse<?> resp = new GenericResponse<>();
		try {
			List<OrderTracking> orderList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(orderList)) {
				orderList = orderTrackingRepository.findByStatusAndOrderCodeIn(Constants.OrderStatus.PLACED,orderCodes);
			} else {
				//NELLA CASISTICA IN CUI VENISSE PASSATA UNA orderCode VUOTA PROCEDO IN ORDINE DI TICKET A SMARCARE GLI ORDINI RICEVUTI
				orderList = allOrdersQuery();
			}
			if(!CollectionUtils.isEmpty(orderList)) {
				for(OrderTracking order :orderList) {
					log.info("Prendendo in consegna e salvando il seguente ordine: {}",order.getOrderCode());
					order.setStatus(Constants.OrderStatus.DELIVERED);
					order.setDtFine(String.valueOf(LocalDateTime.now()));
					orderTrackingRepository.save(order);
				}
			}
		} catch (Exception e) {
			log.error("Errore durante la business logic di statusOrder: {}", e.getMessage());
			return awesomePizzaUtils.buildKoResponse();
		}
		return resp;
	}
}