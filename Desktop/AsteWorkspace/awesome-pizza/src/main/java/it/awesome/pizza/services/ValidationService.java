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

//
//	private Query buildQuery(String multiCompany, String gasDay, String verBusiness, List<String> remi) {
//
//		Query query = new Query();
//
//		query.addCriteria(Criteria.where(Constants.MULTICOMPANY).is(multiCompany));
//
//		query.addCriteria(Criteria.where(Constants.GAS_DAY).is(gasDay));
//
//		query.addCriteria(Criteria.where(Constants.ALLOC_VERBUSINESS).is(Integer.valueOf(verBusiness)));
//
//		query.addCriteria(new Criteria().and(Constants.REMI_COD).in(remi));
//
//		return query;
//
//	}
}
