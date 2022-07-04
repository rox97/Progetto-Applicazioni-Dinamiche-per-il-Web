package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import it.univr.webapp.models.orders.AgentsEntity;
import it.univr.webapp.models.orders.CustomerEntity;
import it.univr.webapp.models.orders.OrdersEntity;
import it.univr.webapp.models.users.UserEntity;
import it.univr.webapp.repositories.orders.AgentsRepository;
import it.univr.webapp.repositories.orders.CustomersRepository;
import it.univr.webapp.repositories.orders.OrderRepository;
import it.univr.webapp.repositories.users.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    ////////////////////////////////////////ORDERS QUERY////////////////////////////////////////

    public List<OrdersEntity> allOrders(){
        return orderRepository.findAll();
    }

    public OrdersEntity orderByOrdNum(int ordNum){
        return orderRepository.findById(ordNum).get();
    }

    public List<OrdersEntity> ordersByAgentCode(String agentCode){

        return orderRepository.findByAgent(agentsRepository.findById(agentCode).get());
    }

    public List<OrdersEntity> ordersByCustCode(String custCode){

        return orderRepository.findByCustomer(customersRepository.findById(custCode).get());
    }

    public Boolean existsOrderByOrdNum(int ordNum){
        return orderRepository.existsById(ordNum);
    }


    ////////////////////////////////////////AGENTS QUERY////////////////////////////////////////


    public List<AgentsEntity> allAgents(){
        return agentsRepository.findByOrderByAgentCodeAsc();
    }

    public AgentsEntity agentByAgentCode(String agentCode){
        return agentsRepository.findById(agentCode).get();
    }

    ////////////////////////////////////////CUSTOMERS QUERY////////////////////////////////////////


    public List<CustomerEntity> allCustomers(){
        return customersRepository.findByOrderByCustCodeAsc();
    }

    public CustomerEntity customerByCustCode(String custCode){
        return customersRepository.findById(custCode).get();
    }


    ////////////////////////////////////////USERS QUERY////////////////////////////////////////

    public List<UserEntity> allUsers(){
        return userRepository.findAll();
    }

    public UserEntity userByUserCode(String user_code){ return userRepository.findById(user_code).get();}

    public UserEntity userByUserAndPswd(String user_code,String user_password) {
        return userRepository.findByUserCodeAndAndUserPassword(user_code, user_password);
    }
}
