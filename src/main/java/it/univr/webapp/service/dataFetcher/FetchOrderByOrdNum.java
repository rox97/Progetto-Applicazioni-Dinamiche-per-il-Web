package it.univr.webapp.service.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import it.univr.webapp.models.OrderRepository;
import it.univr.webapp.models.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchOrderByOrdNum implements DataFetcher<OrdersEntity> {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrdersEntity get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        int ordNum=dataFetchingEnvironment.getArgument("ordNum");
        return orderRepository.findById(ordNum).get();
    }
}
