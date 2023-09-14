package it.awesome.pizza.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.awesome.pizza.database.documents.OrderTracking;

@Repository
public interface OrderTrackingRepository
		extends MongoRepository<OrderTracking, String>{
	
	public List<OrderTracking> findByOrderCode(String orderCode);
	public List<OrderTracking> findByStatusAndOrderCodeIn(String status,List<String> orderCodes);
	
}