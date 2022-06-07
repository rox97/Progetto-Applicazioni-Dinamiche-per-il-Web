package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import it.univr.webapp.repositories.AgentsRepository;
import it.univr.webapp.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

}
