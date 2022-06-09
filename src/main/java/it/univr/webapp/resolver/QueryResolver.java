package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import it.univr.webapp.models.AgentsEntity;
import it.univr.webapp.models.CustomerEntity;
import it.univr.webapp.models.OrdersEntity;
import it.univr.webapp.repositories.AgentsRepository;
import it.univr.webapp.repositories.CustomersRepository;
import it.univr.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AgentsRepository agentsRepository;

    @Autowired
    CustomersRepository customersRepository;

    public List<OrdersEntity> allOrders(){
        return orderRepository.findAll();
    }

    public List<CustomerEntity> allCustomers(){
        return customersRepository.findAll();
    }

    public OrdersEntity ordersByOrdNum(int ordNum){
        return orderRepository.findById(ordNum).get();
    }

    public List<OrdersEntity> ordersByAgentCode(String agentCode){

        return orderRepository.findByAgent(agentsRepository.findById(agentCode).get());
    }

    public List<OrdersEntity> ordersByCustCode(String custCode){

        return orderRepository.findByCustomer(customersRepository.findById(custCode).get());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////



    public List<AgentsEntity> allAgents(){
        return agentsRepository.findAll();
    }

    public AgentsEntity agentByAgentCode(String agentCode){
        return agentsRepository.findById(agentCode).get();
    }

    public CustomerEntity customerByCustCode(String custCode){
        return customersRepository.findById(custCode).get();
    }

}
