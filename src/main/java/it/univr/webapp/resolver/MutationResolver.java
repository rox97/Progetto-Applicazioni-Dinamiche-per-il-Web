package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import it.univr.webapp.exception.OrderNotFoundException;
import it.univr.webapp.input.UpdateOrder;
import it.univr.webapp.models.OrdersEntity;
import it.univr.webapp.repositories.AgentsRepository;
import it.univr.webapp.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private AgentsRepository agentsRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public int deleteOrder(int ordNum){
        return orderRepository.deleteByOrdNum(ordNum);
    }

    @Transactional
    public OrdersEntity updateOrder(UpdateOrder input){
        OrdersEntity order = orderRepository.findById(input.getOrdNum()).orElseThrow(() -> new OrderNotFoundException(input.getOrdNum()));

        if(input.getOrdAmount()!=null){
            order.setOrdAmount(input.getOrdAmount());
        }
        if(input.getAdvanceAmount()!=null){
            order.setAdvanceAmount(input.getAdvanceAmount());
        }
        if(input.getOrdDate()!=null){
            order.setOrdDate(input.getOrdDate());
        }
        if(input.getOrdDescription()!=null) {
            order.setOrdDescription(input.getOrdDescription());
        }

        return order;
    }

}
