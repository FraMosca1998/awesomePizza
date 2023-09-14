package it.awesome.pizza.apis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.awesome.pizza.apis.mediatypes.MediaType;
import it.awesome.pizza.apis.requests.PlaceOrderRequest;
import it.awesome.pizza.common.rest.GenericResponse;
import it.awesome.pizza.services.AsyncService;
import it.awesome.pizza.services.AwesomePizzaService;
import it.awesome.pizza.services.ValidationService;
import it.awesome.pizza.utils.AwesomePizzaUtils;

@RestController
@ApiResponses({ @ApiResponse(code = 200, message = "OK - Request processed successfully"),
		@ApiResponse(code = 400, message = "Bad Request") })
public class AwesomePizzaController {

	private static final Logger log = LoggerFactory.getLogger(AwesomePizzaController.class);

	@Autowired
	private AwesomePizzaService awesomePizzaService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private AwesomePizzaUtils awesomePizzaUtils;
	
	@Autowired
	private AsyncService asyncService;

	@ApiOperation(value = "placeOrder-v01")
	@PostMapping(path = "/placeOrder", produces = MediaType.MEDIATYPE_AWESOME_PIZZA)
	public ResponseEntity<GenericResponse<?>> placeOrder(
			@RequestBody(required = true) PlaceOrderRequest placeOrderRequest, HttpServletRequest httpRequest)
			throws Exception {
		log.info("Start placeOrder sync");

		GenericResponse<?> response = new GenericResponse<>();
		boolean validation = validationService.performValidations(placeOrderRequest);
		if (validation) {
			response = awesomePizzaService.placeOrder(placeOrderRequest);
		} else {
			response = awesomePizzaUtils.buildKoResponse();
		}
		log.info("End placeOrder sync");

		return (validation) ? ResponseEntity.status(HttpStatus.OK).body(response)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}
	
	@ApiOperation(value = "statusOrder-v01")
	@GetMapping(path = "/statusOrder", produces = MediaType.MEDIATYPE_AWESOME_PIZZA)
	public ResponseEntity<GenericResponse<?>> statusOrder(
			@RequestParam(required = true, value = "orderCode") String orderCode
			, HttpServletRequest httpRequest)
			throws Exception {
		log.info("Start statusOrder sync");

		GenericResponse<?> response = new GenericResponse<>();
		if (StringUtils.isNotBlank(orderCode)) {
			response=awesomePizzaService.statusOrder(orderCode);
		} else {
			response=awesomePizzaUtils.buildKoResponse();
		}
		log.info("End statusOrder sync");

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
	
	@ApiOperation(value = "deliverOrder-v01")
	@PutMapping(path = "/deliverOrder", produces = MediaType.MEDIATYPE_AWESOME_PIZZA)
	public ResponseEntity<GenericResponse<?>> deliverOrder(
			@RequestParam(required = true, value = "orderCodes") List<String> orderCodes
			, HttpServletRequest httpRequest)
			throws Exception {
		log.info("Start deliverOrder sync");

		asyncService.deliverOrderAsync(orderCodes);
			
		log.info("End deliverOrder sync");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(awesomePizzaUtils.buildInElabResponse());

	}
}
