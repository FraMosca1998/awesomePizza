package it.awesome.pizza.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.awesome.pizza.apis.requests.PlaceOrderRequest;
@Service
public class ValidationService {

	private static final Logger log = LoggerFactory.getLogger(ValidationService.class);


	public boolean performValidations(PlaceOrderRequest placeOrderRequest) {

		log.info("Start performValidations method");

		boolean validation = false;
		if (null != placeOrderRequest) {
			if (!CollectionUtils.isEmpty(placeOrderRequest.getPizze()) && null != placeOrderRequest.getPizze()) {
				validation = true;
			} else {
				validation = false;
			}
		} else {
			validation = false;
		}

		return validation;
	}
}
