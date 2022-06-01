package it.univr.webapp.service;

import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.beans.BeanProperty;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class GraphQLService {


    @Value("classpath:graphql/order.graphql")
    private Resource resource;

    @Autowired
    private FetchAllOrder fetchAllOrder;

    @Autowired
    private FetchOrderByOrdNum fetchOrderByOrdNum;

    private GraphQL graphQL;

    @PostConstruct
    private void getSchema() throws IOException {
        File schema=resource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry=new SchemaParser().parse(schema);


        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(ExtendedScalars.Date)
                .type("Query",typeWiring->typeWiring
                        .dataFetcher("allOrder",fetchAllOrder)
                        .dataFetcher("orderByOrdNum",fetchOrderByOrdNum))
                .build();

        GraphQLSchema graphQLSchema= new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        graphQL=GraphQL.newGraphQL(graphQLSchema).build();

    }

    public GraphQL getGraphQL(){
        return graphQL;
    }
}

