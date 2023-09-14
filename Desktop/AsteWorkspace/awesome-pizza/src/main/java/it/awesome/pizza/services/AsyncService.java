package it.awesome.pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Autowired
	private AwesomePizzaService awesomePizzaService;

	@Async
	public void deliverOrderAsync(List<String> orderCodes) {
		awesomePizzaService.deliverOrder(orderCodes);
	}

}
