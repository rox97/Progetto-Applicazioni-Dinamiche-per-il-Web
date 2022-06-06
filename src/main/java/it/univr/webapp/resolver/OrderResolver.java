package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import it.univr.webapp.models.OrdersEntity;
import it.univr.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderResolver implements GraphQLQueryResolver {

    @Autowired
    OrderRepository orderRepository;

    public List<OrdersEntity> allOrder(){
        return orderRepository.findAll();
    }

    public OrdersEntity orderByOrdNum(int ordNum){
        return orderRepository.findById(ordNum).get();
    }

}
