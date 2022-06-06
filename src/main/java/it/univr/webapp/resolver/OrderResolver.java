package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import it.univr.webapp.models.AgentsEntity;
import it.univr.webapp.models.OrdersEntity;
import it.univr.webapp.repositories.AgentsRepository;
import it.univr.webapp.repositories.CustomersRepository;
import it.univr.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderResolver implements GraphQLQueryResolver {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AgentsRepository agentsRepository;

    @Autowired
    CustomersRepository customersRepository;

    public List<OrdersEntity> allOrder(){
        return orderRepository.findAll();
    }

    public OrdersEntity orderByOrdNum(int ordNum){
        return orderRepository.findById(ordNum).get();
    }

    /*public List<OrdersEntity> ordersByAgentCode(String agentCode){

        return orderRepository.findByAgentCode(agentsRepository.findById(agentCode).get().getAgentCode());
    }*/

    /////////////////////////////////////////////////////////////////////////////////////////////////



    public List<AgentsEntity> allAgents(){
        return agentsRepository.findAll();
    }

    public AgentsEntity agentByAgentCode(String agentCode){
        return agentsRepository.findById(agentCode).get();
    }

}
