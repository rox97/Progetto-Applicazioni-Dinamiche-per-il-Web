package it.univr.webapp.controller;

import graphql.ExecutionResult;
import it.univr.webapp.models.OrderRepository;
import it.univr.webapp.service.GraphQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger LOGGER= LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private GraphQLService graphQLService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<ExecutionResult> getAllOrders(@RequestBody String query){
        try{
            ExecutionResult executionResult=graphQLService.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        }catch(Exception e) {
            LOGGER.info("error occurred in method getAllEmployee :{}",e.getMessage());
            throw new RuntimeException(new Error((e.getMessage())));
        }

    }
}
