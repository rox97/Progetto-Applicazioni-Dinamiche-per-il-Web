package it.univr.webapp.service.dataFetcher;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import it.univr.webapp.models.CustomerEntity;
import it.univr.webapp.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FetchAllCustomers implements DataFetcher<List<CustomerEntity>> {
    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public List<CustomerEntity> get(DataFetchingEnvironment arg0) throws Exception {
        return customersRepository.findAll();
    }
}