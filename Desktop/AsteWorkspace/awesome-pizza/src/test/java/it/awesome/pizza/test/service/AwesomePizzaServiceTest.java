package it.awesome.pizza.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;

import it.awesome.pizza.common.constants.Constants;
import it.awesome.pizza.common.rest.GenericResponse;
import it.awesome.pizza.database.documents.OrderTracking;
import it.awesome.pizza.database.repository.OrderTrackingRepository;
import it.awesome.pizza.services.AwesomePizzaService;
import it.awesome.pizza.utils.AwesomePizzaUtils;

@ExtendWith(MockitoExtension.class)
public class AwesomePizzaServiceTest {

	    @Mock
	    private MongoTemplate mongoTemplate;

	    @Mock
	    private OrderTrackingRepository orderTrackingRepository;

	    @Mock
	    private AwesomePizzaUtils awesomePizzaUtils;

	    @InjectMocks
	    private AwesomePizzaService awesomePizzaService;

	    @Test
	    void testStatusOrderValidOrder() {
	        String orderCode = "12345";
	        OrderTracking validOrder= new OrderTracking();
	        validOrder.setStatus(Constants.OrderStatus.DELIVERED);
	        validOrder.setOrderCode("12345");
	        List<OrderTracking> orderList = new ArrayList<>();
	        orderList.add(validOrder);

	        when(orderTrackingRepository.findByOrderCode(orderCode)).thenReturn(orderList);

	        GenericResponse<?> response = awesomePizzaService.statusOrder(orderCode);

	        assertEquals(HttpStatus.OK.toString(), response.getResultDescription());
	        assertNotNull(response.getResult());
	        assertEquals(orderCode, response.getResult().getResult());
	        assertEquals("DELIVERED", response.getResult().getMessage());
	    }

	    @Test
	    void testStatusOrderNoOrder() {
	        String orderCode = "54321";
	        List<OrderTracking> emptyOrderList = new ArrayList<>();

	        when(orderTrackingRepository.findByOrderCode(orderCode)).thenReturn(emptyOrderList);

	        GenericResponse<?> response = awesomePizzaService.statusOrder(orderCode);

	        assertEquals(HttpStatus.OK.toString(), response.getResultDescription());
	        assertNotNull(response.getResult());
	        assertEquals(orderCode, response.getResult().getResult());
	        assertEquals(Constants.OrderStatus.NO_ORDER, response.getResult().getMessage());
	    }

	    @Test
	    void testStatusOrderException() {
	        String orderCode = "99999";


	        GenericResponse<?> response = awesomePizzaService.statusOrder(orderCode);

	        assertNotNull(response);
	        assertEquals(Constants.OrderStatus.NO_ORDER, response.getResult().getMessage());
	    }
	}

