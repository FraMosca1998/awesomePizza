package it.awesome.pizza.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import it.awesome.pizza.common.constants.Constants;
import it.awesome.pizza.common.rest.GenericResponse;
import it.awesome.pizza.common.rest.Result;

@Component
public class AwesomePizzaUtils {
	
	public GenericResponse<?> buildOkResponse() {
		GenericResponse<?> resp = new GenericResponse<>();
		Result resObj = new Result(Constants.OK, Constants.ELABORATO);
		resp.setResult(resObj);
		resp.setResultDescription(HttpStatus.OK.toString());
		return resp;
	}
	
	public GenericResponse<?> buildKoResponse() {
		GenericResponse<?> resp = new GenericResponse<>();
		Result resObj = new Result(Constants.KO, Constants.IN_ERRORE);
		resp.setResult(resObj);
		resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		resp.setResultDescription(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return resp;
	}
	
	public GenericResponse<?> buildInElabResponse() {
		GenericResponse<?> resp = new GenericResponse<>();
		Result resObj = new Result(Constants.OK, Constants.IN_ELABORAZIONE);
		resp.setResult(resObj);
		resp.setStato(Constants.IN_ELABORAZIONE);
		resp.setResultDescription(HttpStatus.ACCEPTED.toString());
		return resp;
	}
}