package it.univr.webapp.service;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import it.univr.webapp.models.CustomerEntity;
import it.univr.webapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FetchAllCustomers implements DataFetcher<List<CustomerEntity>> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> get(DataFetchingEnvironment arg0) throws Exception {
        return customerRepository.findAll();
    }
}