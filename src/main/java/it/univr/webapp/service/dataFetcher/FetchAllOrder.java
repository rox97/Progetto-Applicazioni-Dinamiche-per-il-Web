package it.univr.webapp.service.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import it.univr.webapp.models.OrdersEntity;
import it.univr.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FetchAllOrder implements DataFetcher<List<OrdersEntity>> {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrdersEntity> get(DataFetchingEnvironment arg0) throws Exception {
        return orderRepository.findAll();
    }
}
