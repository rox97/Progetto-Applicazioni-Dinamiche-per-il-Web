package it.univr.webapp.service.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import it.univr.webapp.models.AgentsEntity;
import it.univr.webapp.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchAllAgents implements DataFetcher<List<AgentsEntity>> {
    @Autowired
    private AgentsRepository agentsRepository;

    @Override
    public List<AgentsEntity> get(DataFetchingEnvironment arg0) throws Exception{
        return agentsRepository.findAll();
    }
}
