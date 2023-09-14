package it.awesome.pizza.common.rest;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.awesome.pizza.common.rest.Result;


@ApiModel
@JsonInclude(value = Include.NON_NULL)
public class GenericResponse<T> implements  Serializable {

	private static final long serialVersionUID = 5007807046893745317L;

	@ApiModelProperty(value = "Api result", required = false)
	private T data;

	@ApiModelProperty(value = "Api result description", required = true)
	@JsonProperty("status_description")
	private String resultDescription;

	@ApiModelProperty(value = "Api execution time", required = true, example = "12")
	@JsonProperty("execution_time")
	private Long executionTime;

	// Error support part
	@ApiModelProperty(value = "Http result status", required = false, example = "401")
	private HttpStatus status;

	@ApiModelProperty(value = "Timestamp of the error", required = false)
	private LocalDateTime timestamp;

	@ApiModelProperty(value = "Http path request", required = false, example = "http://<ms_endpoint>/<api>")
	private String path;

	@ApiModelProperty(value = "Http method request", required = false, example = "GET")
	private String method;
	
	@ApiModelProperty(value = "Execution status of the request", required = false)
	private String stato; 
	
	// Pagination part
	@ApiModelProperty(value = "Total number of elemens", required = false, example = "150")
	private Integer numberOfElements;

	@ApiModelProperty(value = "Total number of pages", required = false, example = "3")
	private Integer numberOfPages;
	
	@ApiModelProperty(value = "Result", required = false, example = "")
	private Result result;
	
	public GenericResponse() {
		super();
	}
	
	public GenericResponse(T data, String resultDescription) {
		super();
		this.data = data;
		this.resultDescription = resultDescription;
	}
	
	public GenericResponse(T data, String resultDescription,String stato) {
		super();
		this.data = data;
		this.resultDescription = resultDescription;
		this.stato = stato;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public Long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "GenericResponse [data=" + data + ", resultDescription=" + resultDescription + ", executionTime="
				+ executionTime + ", status=" + status + ", timestamp=" + timestamp + ", path=" + path + ", method="
				+ method + ", stato=" + stato + ", numberOfElements=" + numberOfElements + ", numberOfPages="
				+ numberOfPages + ", result=" + result + "]";
	}
}
