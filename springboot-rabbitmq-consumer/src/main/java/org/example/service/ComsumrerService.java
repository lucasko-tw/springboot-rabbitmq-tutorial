package org.example.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Product;
import org.springframework.stereotype.Component;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class ComsumrerService {

    private static final Logger log = LogManager.getLogger(ComsumrerService.class);

    public ComsumrerService() {
        
    }

    public void receiveMessage(  Map<String, String> message) {
       // .getClass().log.info("Received <" + message + ">");
        //String productname =  product.getProduct();
    	
    	log.info("Message processed...");
        System.out.println("product >>> "+message.get("id"));
      
        try {
        	System.out.println("sleeping 5 sec ... ");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
